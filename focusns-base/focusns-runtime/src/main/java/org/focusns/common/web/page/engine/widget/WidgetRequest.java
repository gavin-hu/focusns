package org.focusns.common.web.page.engine.widget;

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
import javax.servlet.http.HttpServletRequestWrapper;

import org.focusns.common.web.page.config.WidgetConfig;

public class WidgetRequest extends HttpServletRequestWrapper implements HttpServletRequest {

    public WidgetRequest(HttpServletRequest request, WidgetConfig widgetConfig, String requestType) {
        super(request);
        super.setAttribute("widgetConfig", widgetConfig);
        super.setAttribute("requestType", requestType);
    }

    public WidgetConfig getWidgetConfig() {
        return (WidgetConfig) super.getAttribute("widgetConfig");
    }

    public String getRequestType() {
        return (String) super.getAttribute("requestType");
    }
}
