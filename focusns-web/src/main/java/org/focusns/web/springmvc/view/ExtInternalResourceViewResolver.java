package org.focusns.web.springmvc.view;

/*
 * #%L
 * FocusSNS Web
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

import java.util.Locale;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class ExtInternalResourceViewResolver extends InternalResourceViewResolver implements ResourceLoaderAware {

    private String pluginPrefix = "classpath:/META-INF/widgets/";
    private String pluginSuffix = ".jsp";
    //
    private ResourceLoader resourceLoader;

    public String getPluginPrefix() {
        return pluginPrefix;
    }

    public void setPluginPrefix(String pluginPrefix) {
        this.pluginPrefix = pluginPrefix;
    }

    public String getPluginSuffix() {
        return pluginSuffix;
    }

    public void setPluginSuffix(String pluginSuffix) {
        this.pluginSuffix = pluginSuffix;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    protected boolean canHandle(String viewName, Locale locale) {
        return super.canHandle(viewName, locale) && isViewExist(viewName, locale);
    }

    private boolean isViewExist(String viewName, Locale locale) {
        //
        if (viewName == null || viewName.startsWith("forward:") || viewName.startsWith("redirect:")) {
            return true;
        }
        //
        String viewLocation = null;
        if (viewName.startsWith("plugin:")) {
            viewLocation = getPluginPrefix() + viewName.substring("plugin:".length()) + getPluginSuffix();
        } else {
            viewLocation = getPrefix() + viewName + getSuffix();
        }
        //

        Resource resource = resourceLoader.getResource(viewLocation);
        return resource.exists();
    }
}
