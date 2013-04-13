package org.focusns.web.helper;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlTemplateHelper {

    /** Captures URI template variable names. */
    private static final Pattern NAMES_PATTERN = Pattern.compile("\\{([^/]+?)\\}");

    /** Replaces template variables in the URI template. */
    private static final String DEFAULT_VARIABLE_PATTERN = "([^/]+)";

    private static Map<String, String> variablePatternMap;

    private List<UriTemplate> uriTemplates = new ArrayList<UriTemplate>();

    public UrlTemplateHelper(String[] uriPatterns) {
        this(uriPatterns, new HashMap<String, String>());
    }

    public UrlTemplateHelper(String[] uriPatterns, Map<String, String> variablePatternMap) {
        if (!variablePatternMap.containsKey("more")) {
            variablePatternMap.put("more", "(.*)");
        }
        //
        UrlTemplateHelper.variablePatternMap = variablePatternMap;
        //
        for (String uriPattern : uriPatterns) {
            this.uriTemplates.add(new UriTemplate(uriPattern));
        }
    }

    public Map<String, String> match(String uri) {
        Map<String, String> pathVariables = new HashMap<String, String>();
        //
        int count = 0;
        for (UriTemplate uriTemplate : uriTemplates) {
            if (uriTemplate.matches(uri)) {
                pathVariables.putAll(uriTemplate.match(uri));
                count++;
            }
        }
        //
        if (count > 1) {
            throw new IllegalArgumentException("More than one UriTemplate matched this uri : " + uri);
        }
        //
        return pathVariables;
    }

    private static class UriTemplate {

        private Pattern pattern;
        //
        private List<String> variableNames;

        public UriTemplate(String uriTemplate) {
            Parser parser = new Parser(uriTemplate);
            this.pattern = parser.getMatchPattern();
            this.variableNames = parser.getVariableNames();
        }

        public boolean matches(String uri) {
            return pattern.matcher(uri).find();
        }

        public Map<String, String> match(String uri) {
            Map<String, String> pathVariables = new HashMap<String, String>();
            //
            Matcher matcher = this.pattern.matcher(uri);
            if (matcher.find()) {
                for (int i = 1; i <= variableNames.size(); i++) {
                    String virableName = variableNames.get(i - 1);
                    String cirableValue = matcher.group(i);
                    pathVariables.put(virableName, cirableValue);
                }
            }
            //
            return pathVariables;
        }

    }

    private static class Parser {

        private final List<String> variableNames = new LinkedList<String>();

        private final StringBuilder patternBuilder = new StringBuilder();

        private Parser(String uriTemplate) {
            Assert.hasText(uriTemplate, "'uriTemplate' must not be null");
            Matcher m = NAMES_PATTERN.matcher(uriTemplate);
            int end = 0;
            while (m.find()) {
                this.patternBuilder.append(quote(uriTemplate, end, m.start()));
                String match = m.group(1);
                int colonIdx = match.indexOf(':');
                if (colonIdx == -1) {
                    this.variableNames.add(match);
                    String variablePattern = variablePatternMap.get(match);
                    if (StringUtils.hasText(variablePattern)) {
                        this.patternBuilder.append(variablePattern);
                    } else {
                        this.patternBuilder.append(DEFAULT_VARIABLE_PATTERN);
                    }
                } else {
                    if (colonIdx + 1 == match.length()) {
                        throw new IllegalArgumentException("No custom regular expression specified after ':' in \"" + match + "\"");
                    }
                    String variablePattern = match.substring(colonIdx + 1, match.length());
                    this.patternBuilder.append('(');
                    this.patternBuilder.append(variablePattern);
                    this.patternBuilder.append(')');
                    String variableName = match.substring(0, colonIdx);
                    this.variableNames.add(variableName);
                }
                end = m.end();
            }
            this.patternBuilder.append(quote(uriTemplate, end, uriTemplate.length()));
            int lastIdx = this.patternBuilder.length() - 1;
            if (lastIdx >= 0 && this.patternBuilder.charAt(lastIdx) == '/') {
                this.patternBuilder.deleteCharAt(lastIdx);
            }
        }

        private String quote(String fullPath, int start, int end) {
            if (start == end) {
                return "";
            }
            return fullPath.substring(start, end);
        }

        private List<String> getVariableNames() {
            return Collections.unmodifiableList(this.variableNames);
        }

        private Pattern getMatchPattern() {
            return Pattern.compile(this.patternBuilder.toString());
        }
    }

}
