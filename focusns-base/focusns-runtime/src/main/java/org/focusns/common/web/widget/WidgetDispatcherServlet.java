package org.focusns.common.web.widget;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

public class WidgetDispatcherServlet extends DispatcherServlet {

    private static final String DEFAULT_CONTEXT_CONFIG_LOCATION = "/WEB-INF/spring/dispatcherContext-widget.xml";

    @Override
    public String getContextConfigLocation() {
        if (super.getContextConfigLocation() == null) {
            return DEFAULT_CONTEXT_CONFIG_LOCATION;
        }
        return super.getContextConfigLocation();
    }

    @Override
    protected String getDefaultViewName(HttpServletRequest request) throws Exception {
        //

        //
        return super.getDefaultViewName(request);
    }

    @Override
    protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //
        String requestType = (String) request.getAttribute("requestType");
        if (!"action".equals(requestType)) {
            super.render(mv, request, response);
        }
    }

}
