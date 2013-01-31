
package org.focusns.web.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ThemeInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception {
		//
        if(modelAndView==null) {
            return ;
        }
        //
        String viewName = modelAndView.getViewName();
        if(viewName!=null && viewName.contains(":")) {
            return ;
        }
		//
        modelAndView.setViewName("themes/default/layout");
	}
	
}
