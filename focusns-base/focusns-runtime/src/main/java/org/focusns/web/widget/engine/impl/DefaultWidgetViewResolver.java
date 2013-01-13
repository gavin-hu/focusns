/*
 * Copyright (C) 2011-2013 FocusSNS.
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
package org.focusns.web.widget.engine.impl;

import freemarker.template.Template;
import org.focusns.web.widget.engine.WidgetView;
import org.focusns.web.widget.engine.WidgetViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.util.Locale;

public class DefaultWidgetViewResolver implements WidgetViewResolver {

	private FreeMarkerConfig freeMarkerConfig;
	
	private String prefix = "/WEB-INF/widgets/";
	private String suffix = ".ftl";

	public DefaultWidgetViewResolver() {
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public void setFreeMarkerConfig(FreeMarkerConfig freeMarkerConfig) {
		this.freeMarkerConfig = freeMarkerConfig;
	}

	public WidgetView resolve(Object viewName, Locale locale) throws Exception {
		if(viewName instanceof String) {
			String viewPath = getViewPath((String)viewName);
			Template template = freeMarkerConfig.getConfiguration().getTemplate(viewPath, locale);
			//
			return new DefaultWidgetView(template);
		}
		//
		throw new NullPointerException("WidgetView not found!");
	}
	
	private String getViewPath(String viewName) {
		if(viewName.startsWith("/")) {
			viewName = viewName.substring(1);
		}
		//
		return prefix + viewName + suffix;
	}

}
