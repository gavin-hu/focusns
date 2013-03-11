package org.focusns.web.springmvc.resolver;

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


import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Locale;

public class ExtInternalResourceViewResolver extends InternalResourceViewResolver
    implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    protected boolean canHandle(String viewName, Locale locale) {
        return super.canHandle(viewName, locale) && isViewExist(viewName,  locale);
    }

    private boolean isViewExist(String viewName, Locale locale) {
        //
        if(viewName==null ||
                viewName.startsWith("forward:") ||
                viewName.startsWith("redirect:")) {
            return true;
        }
        //
        String viewLocation = getPrefix() + viewName + getSuffix();
        Resource resource = resourceLoader.getResource(viewLocation);
        return resource.exists();
    }
}
