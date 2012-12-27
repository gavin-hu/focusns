/*
 * Copyright (C) 2012 FocuSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.focusns.web.page.config;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractPageConfigFactory implements PageConfigFactory {
	
	private boolean cacheable;
	//
	private Map<String, PageConfig> cache = new LinkedHashMap<String, PageConfig>();
	
	public void setCacheable(boolean cacheable) {
		this.cacheable = cacheable;
	}

	public PageConfig findPage(String path) {
		try {
			if(cacheable && cache.containsKey(path)) {
				return cache.get(path);
			}
			//
			PageConfig pageConfig = loadPage(path);
			if(cacheable) {
				cache.put(path, pageConfig);
			}
			//
			return pageConfig;
		} catch (Exception e) {
			throw new PageConfigException("PageConfig load exception!", e);
		}
	}
	
	protected abstract PageConfig loadPage(String path) throws Exception;

}
