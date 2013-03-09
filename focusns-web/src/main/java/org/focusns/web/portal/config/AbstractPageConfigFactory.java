package org.focusns.web.portal.config;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */


import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractPageConfigFactory implements PageConfigFactory {
	
	private boolean cacheable;
	//
	private Map<String, PageConfig> cache = new LinkedHashMap<String, PageConfig>();
	
	public void setCacheable(boolean cacheable) {
		this.cacheable = cacheable;
	}

	protected abstract Map<String, PageConfig> loadPages() throws Exception;

    public PageConfig findPage(String category, String path) throws Exception {
        //
        String key = generateKey(category, path);
        if(cacheable && cache.containsKey(key)) {
            return cache.get(key);
        }
        //
        this.cache = loadPages();
        //
        return cache.get(key);

    }

    protected String generateKey(String category, String path) {
        if(StringUtils.hasText(category)) {
            return category + path;
        }
        //
        return path;
    }

}
