/*
 * Copyright (C) 2012 FocusSNS.
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
package org.focusns.web.widget.security;

import org.focusns.web.page.config.WidgetConfig;
import org.focusns.web.widget.annotation.Resource;
import org.focusns.web.widget.engine.WidgetFilter;
import org.focusns.web.widget.engine.WidgetMethod;
import org.focusns.web.widget.engine.WidgetMethodResolver;
import org.focusns.web.widget.engine.impl.DefaultWidgetMethodResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

public class SecurityWidgetFilter implements WidgetFilter {

    private WidgetMethodResolver methodResolver = new DefaultWidgetMethodResolver();
    
    public void doFilter(HttpServletRequest request, HttpServletResponse response, 
            List<WidgetConfig> widgetConfigList) throws Exception {
        //
        for(Iterator<WidgetConfig> iter=widgetConfigList.iterator(); iter.hasNext();) {
            //
            WidgetConfig widgetConfig = iter.next();
            WidgetMethod widgetMethod = methodResolver.resolve(
                   widgetConfig.getClassName(), widgetConfig.getMethodName());
            //
            HttpSession session = request.getSession();
            ServletContext application = session.getServletContext();
            List<Resource> resourceList = widgetMethod.getMethodResources();
            for(Resource resource : resourceList) {
                //
                if(Resource.Scope.PARAMETER==resource.scope()) {
                    for(String required : resource.required()) {
                        if(request.getParameter(required)==null) {
                            iter.remove();
                        }
                    }
                }
                //
                if(Resource.Scope.REQUEST==resource.scope()) {
                    for(String required : resource.required()) {
                        if(request.getAttribute(required)==null) {
                            iter.remove();
                        }
                    }
                }
                //
                if(Resource.Scope.SESSION==resource.scope()) {
                    for(String required : resource.required()) {
                        if(session.getAttribute(required)==null) {
                            iter.remove();
                        }
                    }
                }
                //
                if(Resource.Scope.APPLICATION==resource.scope()) {
                    for(String required : resource.required()) {
                        if(application.getAttribute(required)==null) {
                            iter.remove();
                        }
                    }
                }
            }
            
        }
    }
    
}
