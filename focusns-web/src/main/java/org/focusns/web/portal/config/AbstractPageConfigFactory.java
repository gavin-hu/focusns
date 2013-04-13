package org.focusns.web.portal.config;

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

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public abstract class AbstractPageConfigFactory implements PageConfigFactory {

    private static final String[] PARAM_NAMES = new String[] { "mode", "category" };

    private boolean cacheable;
    //
    private Map<String, PageConfig> cache = new LinkedHashMap<String, PageConfig>();

    public void setCacheable(boolean cacheable) {
        this.cacheable = cacheable;
    }

    protected abstract Map<String, PageConfig> loadPages() throws Exception;

    public PageConfig findPage(String path, Map<String, String> paramsMap) throws Exception {
        //
        String key = generateKey(path, paramsMap);
        if (cacheable && cache.containsKey(key)) {
            return cache.get(key);
        }
        //
        this.cache = loadPages();
        //
        return cache.get(key);

    }

    protected String generateKey(String path, Map<String, String> paramsMap) {
        if (paramsMap.isEmpty()) {
            return path;
        }
        //
        StringBuilder sb = new StringBuilder(path);
        for (String paramName : PARAM_NAMES) {
            String paramValue = paramsMap.get(paramName);
            if (StringUtils.hasText(paramValue)) {
                sb.append(paramName).append("=").append(paramsMap.get(paramName));
            }
        }
        //
        return sb.toString();
    }
}
