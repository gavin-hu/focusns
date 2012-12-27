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
package org.focusns.web.widget.engine;

import org.focusns.web.page.config.WidgetConfig;
import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;

public class WidgetInvocation {

	private WidgetConfig widgetConfig;
	private WidgetRequest widgetRequest;
	private WidgetResponse widgetResponse;
	
	public WidgetInvocation(WidgetConfig widgetConfig,
			WidgetRequest widgetRequest, WidgetResponse widgetResponse) {
		this.widgetConfig = widgetConfig;
		this.widgetRequest = widgetRequest;
		this.widgetResponse = widgetResponse;
	}
	
	public WidgetConfig getWidgetConfig() {
		return widgetConfig;
	}
	
	public WidgetRequest getWidgetRequest() {
		return widgetRequest;
	}
	
	public WidgetResponse getWidgetResponse() {
		return widgetResponse;
	}
	
}
