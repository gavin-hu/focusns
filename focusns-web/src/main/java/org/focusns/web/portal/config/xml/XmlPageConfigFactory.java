package org.focusns.web.portal.config.xml;

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

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import org.focusns.common.xml.XmlParser;
import org.focusns.web.portal.config.*;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

public class XmlPageConfigFactory extends AbstractPageConfigFactory implements PageConfigFactory, ResourceLoaderAware {

    private String prefix = "/WEB-INF/portal/";
    private String suffix = ".xml";

    private ResourceLoader resourceLoader;

    private XmlParser xmlParser = new XmlParser();

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    protected Map<String, PageConfig> loadPages() throws Exception {
        //
        Map<String, PageConfig> pageConfigMap = new HashMap<String, PageConfig>();
        //
        ResourcePatternResolver rpr = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        Resource[] resources = rpr.getResources(prefix + "/**/*" + suffix);
        for (Resource resource : resources) {
            List<PageConfig> pageConfigList = XmlPageConfigParser.parse(resource.getInputStream());
            for (PageConfig pageConfig : pageConfigList) {
                String key = generateKey(pageConfig);
                pageConfigMap.put(key, pageConfig);
            }
        }
        // load page plugins
        Resource[] pluginResources = rpr.getResources("classpath*:META-INF/focusns-plugin.xml");
        for(Resource pluginResource : pluginResources) {
            List<PageConfig> pageConfigPlugins = XmlPageConfigParser.parse(pluginResource.getInputStream());
            for(PageConfig pageConfigPlugin : pageConfigPlugins) {
                String key = generateKey(pageConfigPlugin);
                PageConfig pageConfig = pageConfigMap.get(key);
                if(pageConfig != null) {
                    pageConfig.plugin(pageConfigPlugin);
                    pageConfigMap.put(key, pageConfig);
                }
            }
        }
        //
        return pageConfigMap;
    }

    public void loadPluginPages(List<PageConfig> pageConfigList) throws Exception {

    }

    private String generateKey(PageConfig pageConfig) {
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("mode", pageConfig.getMode());
        paramsMap.put("category", pageConfig.getCategory());
        return generateKey(pageConfig.getPath(), paramsMap);
    }

}
