package org.focusns.common.web.page.config;

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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class PageConfigKey {

    private static final List<String> PARAM_NAMES_EXCLUDE = Arrays.asList(new String[] { "path", "layout" });

    public static String generateKey(PageConfig pageConfig) {
        return generateKey(pageConfig.getPath(), pageConfig.getParameters());
    }

    public static String generateKey(String path, Map<String, ?> paramsMap) {
        if (paramsMap.isEmpty()) {
            return path;
        }
        //
        StringBuilder sb = new StringBuilder(path);
        for (String paramName : paramsMap.keySet()) {
            if (PARAM_NAMES_EXCLUDE.contains(paramName)) {
                continue;
            }
            //
            String paramValue = String.valueOf(paramsMap.get(paramName));
            if (StringUtils.hasText(paramValue)) {
                sb.append(paramName).append("=").append(paramsMap.get(paramName));
            }
        }
        //
        return sb.toString();
    }

}
