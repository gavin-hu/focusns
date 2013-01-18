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

import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import org.focusns.common.freemarker.model.DynamicInvoker;
import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;
import org.focusns.web.widget.engine.WidgetView;

public class DefaultWidgetView implements WidgetView {
	
	private Template template;
	
	public DefaultWidgetView(Template template) {
		this.template = template;
	}

	public void render(WidgetRequest request, WidgetResponse widgetResponse) throws Exception {
		//
		SimpleHash model = buildTemplateModel(request);
		//
		template.process(model, widgetResponse.getWriter());
	}
	
	
	private SimpleHash buildTemplateModel(WidgetRequest request) {
		SimpleHash simpleHash = new SimpleHash();
		simpleHash.put(FreemarkerServlet.KEY_REQUEST_PARAMETERS, request.getRequestParameters());
		simpleHash.put(FreemarkerServlet.KEY_REQUEST, request.getRequestAttributes());
		simpleHash.put(FreemarkerServlet.KEY_SESSION, request.getSessionAttributes());
		simpleHash.put(FreemarkerServlet.KEY_APPLICATION, request.getApplicationAttributes());
        simpleHash.put(DynamicInvoker.class.getSimpleName(), new DynamicInvoker(simpleHash));
		//
		return simpleHash;
    }

}
