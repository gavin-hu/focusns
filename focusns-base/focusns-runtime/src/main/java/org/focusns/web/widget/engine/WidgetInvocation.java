
package org.focusns.web.widget.engine;

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


import org.focusns.web.widget.config.WidgetConfig;
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
