
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
