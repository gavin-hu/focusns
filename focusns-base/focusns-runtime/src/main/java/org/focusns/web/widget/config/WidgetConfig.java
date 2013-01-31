
package org.focusns.web.widget.config;

import java.util.HashMap;
import java.util.Map;

public class WidgetConfig {

	private String className;
    private String methodName;
    private Map<String, Object> preferences = new HashMap<String, Object>();
	
	public WidgetConfig(String target) {
        this.className = target.substring(0, target.indexOf("@"));
        this.methodName = target.substring(target.indexOf("@")+1);
	}
	
	public String getClassName() {
		return className;
	}
	
	public String getMethodName() {
		return methodName;
	}

    public Map<String, Object> getPreferences() {
        return preferences;
    }

    public void setPreference(Map<String, Object> preferences) {
        this.preferences = preferences;
    }
}
