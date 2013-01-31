
package org.focusns.web.widget.http;

import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.config.WidgetConfig;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpWidgetRequest implements WidgetRequest {

    private WidgetConfig widgetConfig;
	private Map<String, Object> requestParameters = Collections.emptyMap();
	private Map<String, Object> requestAttributes = Collections.emptyMap();
	private Map<String, Object> sessionAttributes = Collections.emptyMap();
	private Map<String, Object> applicationAttributes = Collections.emptyMap();
	
	public HttpWidgetRequest() {
	}
	
	public HttpWidgetRequest(WidgetConfig widgetConfig,
                             Map<String, ?> requestParameters, Map<String, ?> requestAttributes,
                             Map<String, ?> sessionAttributes, Map<String, ?> applicationAttributes) {
        this.widgetConfig = widgetConfig;
		this.requestParameters = new HashMap<String, Object>(requestParameters);
		this.requestAttributes = new HashMap<String, Object>(requestAttributes);
		this.sessionAttributes = new HashMap<String, Object>(sessionAttributes);
		this.applicationAttributes = new HashMap<String, Object>(applicationAttributes);
	}

    @Override
    public String getWidgetPreference(String name) {
        return (String) widgetConfig.getPreferences().get(name);
    }

    public String getRequestParameter(String name) {
		return (String) this.requestParameters.get(name);
	}

	@SuppressWarnings("unchecked")
	public <T> T getRequestAttribute(String name) {
		return (T) this.requestAttributes.get(name);
	}
	
	public void setRequestAttribute(String name, Object value) {
		this.requestAttributes.put(name, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T getSessionAttribute(String name) {
		return (T) this.sessionAttributes.get(name);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getApplicationAttribute(String name) {
		return (T) this.applicationAttributes.get(name);
	}

    @Override
    public Map<String, Object> getWidgetPreferences() {
        return widgetConfig.getPreferences();
    }

    public Map<String, Object> getRequestParameters() {
		return requestParameters;
	}

	public Map<String, Object> getRequestAttributes() {
		return requestAttributes;
	}

	public Map<String, Object> getSessionAttributes() {
		return sessionAttributes;
	}
	
	public Map<String, Object> getApplicationAttributes() {
		return applicationAttributes;
	}

}
