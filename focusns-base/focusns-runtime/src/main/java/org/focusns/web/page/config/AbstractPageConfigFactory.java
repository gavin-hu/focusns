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
