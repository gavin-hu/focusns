
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
