/*
 * Copyright (C) 2012 FocusSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.focusns.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.focusns.web.page.config.PageConfigException;

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
    
}
