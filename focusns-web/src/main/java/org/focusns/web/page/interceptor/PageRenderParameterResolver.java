package org.focusns.web.page.interceptor;

import org.focusns.common.web.WebUtils;
import org.focusns.common.web.page.engine.PageRenderInterceptor;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectCategory;
import org.focusns.model.core.ProjectFeature;
import org.focusns.service.core.ProjectCategoryService;
import org.focusns.service.core.ProjectFeatureService;
import org.focusns.service.core.ProjectService;
import org.focusns.web.Keys;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class PageRenderParameterResolver implements PageRenderInterceptor, ApplicationContextAware {

    private static final UrlPathHelper urlPathHelper = new UrlPathHelper();

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void beforeRender(HttpServletRequest request, HttpServletResponse response) {
        //
        Map<String, String> parameterMap = WebUtils.getParameterMap(request);
        String lookupPath = urlPathHelper.getLookupPathForRequest(request);
        //
        String projectCode = parameterMap.get(Keys.PARAMETER_PROJECT_CODE);
        if(StringUtils.hasText(projectCode)) {
            //
            ProjectService projectService = applicationContext.getBean(ProjectService.class);
            ProjectCategoryService projectCategoryService = applicationContext.getBean(ProjectCategoryService.class);
            ProjectFeatureService projectFeatureService = applicationContext.getBean(ProjectFeatureService.class);
            //
            Project project = projectService.getProject(projectCode);
            ProjectCategory projectCategory = projectCategoryService.getCategory(project.getCategoryId());
            //
            request.setAttribute(Keys.REQUEST_PROJECT, project);
            request.setAttribute(Keys.REQUEST_PROJECT_CATEGORY, projectCategory);
            //
            List<ProjectFeature> projectFeatures = projectFeatureService.getProjectFeatures(project.getId());
            for(ProjectFeature projectFeature : projectFeatures) {
                if(lookupPath.contains(projectFeature.getCode())) {
                    request.setAttribute(Keys.REQUEST_PROJECT_FEATURE, projectFeature);
                    break;
                }
            }
        }
        //
        Object projectUser = request.getSession().getAttribute(Keys.SESSION_PROJECT_USER);
        if(projectUser!=null) {
            request.setAttribute(Keys.REQUEST_PROJECT_USER, projectUser);
        }
    }

    @Override
    public void afterRender(HttpServletRequest request, HttpServletResponse response) {
    }

}
