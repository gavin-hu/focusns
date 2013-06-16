package org.focusns.common.web.widget.mvc.support;

import java.util.HashMap;
import java.util.Map;

public class Navigator {

    public static final String KEY = "navigator";

    private static ThreadLocal<Navigator> navigatorLocal = new ThreadLocal<Navigator>() {
        @Override
        protected Navigator initialValue() {
            return new Navigator();
        }
    };

    private String navigateTo;
    private Map<String, Object> redirectAttributes = new HashMap<String, Object>();

    private Navigator() {
    }

    public static Navigator get() {
        return navigatorLocal.get();
    }

    public static void reset() {
        navigatorLocal.remove();
    }

    public Navigator withAttribute(String name, Object value) {
        redirectAttributes.put(name, value);
        return this;
    }

    public Map<String, Object> getRedirectAttributes() {
        return redirectAttributes;
    }

    public void navigateTo(String navigateTo) {
        this.navigateTo = navigateTo;
    }

    public String getNavigateTo() {
        return navigateTo;
    }
}
