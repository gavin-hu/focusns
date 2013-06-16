package org.focusns.common.web.tagext.control.view;

import java.util.Map;

public interface ViewRender {

    String renderView(String viewName, Map<String, Object> model);

}
