
package org.focusns.web.servlet;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */


import org.focusns.web.page.config.PageConfigException;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

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
