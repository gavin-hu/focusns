package org.focusns.web.servlet;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.focusns.web.page.config.PageConfigException;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

@SuppressWarnings("serial")
public class DispatcherServlet extends
		org.springframework.web.servlet.DispatcherServlet {

    @Override
	protected void noHandlerFound(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//
		try {
			request.getRequestDispatcher("/portal").forward(request, response);
		} catch (PageConfigException e) {
			response.getWriter().write(e.getMessage());
			super.noHandlerFound(request, response);
		}
	}

    @Override
    protected String getDefaultViewName(HttpServletRequest request) throws Exception {
        return null;
    }

    @Override
    protected View resolveViewName(String viewName, Map<String, Object> model, Locale locale, HttpServletRequest request) throws Exception {
        View view = super.resolveViewName(viewName, model, locale, request);
        //
        if(view instanceof RedirectView) {
            String redirectPath = viewName.substring("redirect:".length());
            FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
            FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
            flashMap.setTargetRequestPath(redirectPath);
            flashMap.putAll(model);
            flashMapManager.saveOutputFlashMap(flashMap, request, null);
            //
            ((RedirectView) view).setExposeModelAttributes(false);
        }
        //
        return view;
    }

    @Override
    protected ModelAndView processHandlerException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //
        ModelAndView mav = super.processHandlerException(request, response, handler, ex);

        //
        return mav;
    }
}
