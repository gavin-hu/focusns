package org.focusns.common.web.support;

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

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.support.ServletContextResourceLoader;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.util.UrlPathHelper;

public class ResourceServlet extends HttpServletBean {

    private UrlPathHelper urlPathHelper = new UrlPathHelper();
    private ResourceLoader resourceLoader;

    //
    @Override
    protected void initServletBean() throws ServletException {
        //
        this.resourceLoader = new ServletContextResourceLoader(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
        String lookupPath = urlPathHelper.getLookupPathForRequest(req);
        Resource resource = resourceLoader.getResource("/WEB-INF" + lookupPath);

        //
        if (resource.exists()) {
            FileCopyUtils.copy(resource.getInputStream(), resp.getOutputStream());
        } else {
            throw new FileNotFoundException(lookupPath);
        }
    }
}
