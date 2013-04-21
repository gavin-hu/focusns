package org.focusns.web.portal;

/*
 * #%L
 * FocusSNS Web
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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectCategory;
import org.focusns.model.core.ProjectFeature;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectCategoryService;
import org.focusns.service.core.ProjectFeatureService;
import org.focusns.service.core.ProjectService;
import org.focusns.web.portal.config.PageConfig;
import org.focusns.web.portal.config.PageConfigFactory;
import org.focusns.web.portal.config.PositionConfig;
import org.focusns.web.portal.config.WidgetConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
public class Portal {

    private static final Log log = LogFactory.getLog(Portal.class);

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectFeatureService featureService;
    @Autowired
    private ProjectCategoryService categoryService;
    @Autowired
    private PageConfigFactory pageConfigFactory;

    @RequestMapping("/portal")
    public String doRender(@RequestParam(required = false) String mode,
            @RequestParam(required = false) String projectCode, @RequestParam String path, WebRequest webRequest)
            throws Exception {
        //
        String categoryCode = null;
        // export project
        if (StringUtils.hasText(projectCode)) {
            Project project = projectService.getProject(projectCode);
            if (project != null) {
                ProjectCategory projectCategory = categoryService.getCategory(project.getCategoryId());
                categoryCode = projectCategory.getCode();
                //
                webRequest.setAttribute("project", project, WebRequest.SCOPE_REQUEST);
                webRequest.setAttribute(Project.KEY, project, WebRequest.SCOPE_REQUEST);
                webRequest.setAttribute("projectCategory", projectCategory, WebRequest.SCOPE_REQUEST);
                webRequest.setAttribute(ProjectCategory.KEY, projectCategory, WebRequest.SCOPE_REQUEST);
                // export feature
                String featureCode = webRequest.getParameter("featureCode");
                if (StringUtils.hasText(featureCode)) {
                    ProjectFeature projectFeature = featureService.getProjectFeature(project.getId(), featureCode);
                    webRequest.setAttribute("projectFeature", projectFeature, WebRequest.SCOPE_REQUEST);
                    webRequest.setAttribute(ProjectFeature.KEY, projectFeature, WebRequest.SCOPE_REQUEST);
                }
            }
        }
        // export ProjectUser
        ProjectUser projectUser = (ProjectUser) webRequest.getAttribute(ProjectUser.KEY, WebRequest.SCOPE_SESSION);
        if (projectUser != null) {
            webRequest.setAttribute("user", projectUser, WebRequest.SCOPE_REQUEST);
            webRequest.setAttribute(ProjectUser.KEY, projectUser, WebRequest.SCOPE_REQUEST);
        }
        //
        PageConfig pageConfig = resolvePage(path, mode, categoryCode);
        Assert.notNull(pageConfig, String.format("Page %s not found!", path));
        //
        processPageConfig(pageConfig, webRequest);
        //
        webRequest.setAttribute("pageConfig", pageConfig, WebRequest.SCOPE_REQUEST);
        //
        return "viewName";
    }

    protected PageConfig resolvePage(String pagePath, String mode, String category) throws Exception {
        Map<String, String> paramsMap = new HashMap<String, String>();
        if (StringUtils.hasText(mode)) {
            paramsMap.put("mode", mode);
        }
        if (StringUtils.hasText(category)) {
            paramsMap.put("category", category);
        }
        //
        return pageConfigFactory.findPage(pagePath, paramsMap);
    }

    protected void processPageConfig(PageConfig pageConfig, WebRequest webRequest) {
        for (PositionConfig positionConfig : pageConfig.getPositionConfigMap().values()) {
            Iterator<WidgetConfig> iter = positionConfig.getWidgetConfigList().iterator();
            //
            while (iter.hasNext()) {
                WidgetConfig widgetConfig = iter.next();
                //
                boolean needRemove = false;
                //
                if (needRemove) {
                    iter.remove();
                }
            }
        }
    }

}
