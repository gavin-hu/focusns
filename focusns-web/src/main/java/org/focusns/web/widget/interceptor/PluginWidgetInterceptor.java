package org.focusns.web.widget.interceptor;

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

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// TODO Performance Tuning
public class PluginWidgetInterceptor extends HandlerInterceptorAdapter implements ResourceLoaderAware {

    private boolean cache = true;
    private ResourceLoader resourceLoader;
    //
    private Map<String, String> pluginViewNameCache = new HashMap<String, String>();

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        //
        if (modelAndView == null) {
            return;
        }
        //
        String viewName = modelAndView.getViewName();
        if (viewName != null && viewName.startsWith("plugin:")) {
            //
            viewName = viewName.substring("plugin:".length());
            Resource resource = resourceLoader.getResource("/WEB-INF/plugins");
            File[] pluginDirs = resource.getFile().listFiles();
            for (File pluginDir : pluginDirs) {
                File targetFile = new File(pluginDir, "/META-INF/widgets/" + viewName + ".jsp");
                if (targetFile.exists()) {
                    viewName = pluginDir.getName() + "/META-INF/widgets/" + viewName;
                }
            }
            //
            modelAndView.setViewName(viewName);
        }
    }

}
