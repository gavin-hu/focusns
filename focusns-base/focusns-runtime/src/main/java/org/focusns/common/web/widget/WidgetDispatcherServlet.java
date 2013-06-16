package org.focusns.common.web.widget;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WidgetDispatcherServlet extends DispatcherServlet {

    private static final String DEFAULT_CONTEXT_CONFIG_LOCATION = "/WEB-INF/spring/dispatcherContext-widget.xml";

    @Override
    public String getContextConfigLocation() {
        if(super.getContextConfigLocation()==null) {
            return DEFAULT_CONTEXT_CONFIG_LOCATION;
        }
        return super.getContextConfigLocation();
    }

    @Override
    protected String getDefaultViewName(HttpServletRequest request) throws Exception {
        //

        //
        return super.getDefaultViewName(request);
    }

    @Override
    protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //
        String requestType = (String) request.getAttribute("requestType");
        if(!"action".equals(requestType)) {
            super.render(mv, request, response);
        }
    }

}
