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
package org.focusns.web.portal;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectCategory;
import org.focusns.model.core.ProjectFeature;
import org.focusns.service.core.ProjectCategoryService;
import org.focusns.service.core.ProjectFeatureService;
import org.focusns.service.core.ProjectService;
import org.focusns.web.page.render.PageRender;
import org.focusns.web.utils.UrlTemplateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UrlPathHelper;

@Controller
public class Portal {
	
	private static final String[] uriPatterns = new String[]{
		"^/{categoryCode}$",
		"^/{projectCode}/{featureCode}$",
        "^/{projectCode}/{featureCode}/{more}$"
	};
	
	private UrlPathHelper urlPathHelper = new UrlPathHelper();
	
	private UrlTemplateHelper urlTemplateHelper = new UrlTemplateHelper(uriPatterns);
	@Autowired
	private PageRender pageRender;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ProjectFeatureService featureService;
	@Autowired
	private ProjectCategoryService categoryService;
	
	@RequestMapping("/portal")
	public String doRender(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pagePath = resolvePagePath(request);
		if( pageRender.matches(pagePath)) {
			pageRender.doRender(request, response);
		}
		//
		return "viewName";
	}
	
	protected String resolvePagePath(HttpServletRequest request) {
		String requestPath = urlPathHelper.getOriginatingRequestUri(request);
		if("/".equals(requestPath) || "".equals(requestPath)) {
			requestPath = "/index";
		}
		//
		Map<String, String> pathVariables = urlTemplateHelper.match(requestPath);
		if(pathVariables.containsKey("categoryCode")) {
			String categoryCode = pathVariables.get("categoryCode");
			//
			ProjectCategory category = categoryService.getCategory(categoryCode);
			if(category!=null) {
				request.setAttribute("category", category);
				return "/".concat(categoryCode);
			}
		}
		//
		if(pathVariables.containsKey("projectCode") && pathVariables.containsKey("featureCode")) {
			String projectCode = pathVariables.get("projectCode");
			String featureCode = pathVariables.get("featureCode");
            String more = pathVariables.get("more");
            //
			Project project = projectService.getProject(projectCode);
			if(project!=null) {
				ProjectCategory category = categoryService.getCategory(project.getCategoryId());
				ProjectFeature feature = featureService.getProjectFeature(project.getId(), featureCode);
				if(category!=null && feature!=null) {
					request.setAttribute("project", project);
					request.setAttribute("category", category);
					request.setAttribute("feature", feature);
                    //
                    if(StringUtils.hasText(more)) {
                        return "/" + category.getCode() + "/" + feature.getCode() + "/" + more;
                    }
					//
					return "/" + category.getCode() + "/" + feature.getCode();
				}
			}
		}
		//
		return requestPath;
	}
	
}
