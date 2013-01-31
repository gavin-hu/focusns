
package org.focusns.web.helper;

import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectFeature;
import org.springframework.web.context.request.WebRequest;

public abstract class WebRequestHelper {
    
    public static Project getProject(WebRequest webRequest) {
        return  (Project) webRequest.getAttribute("project", WebRequest.SCOPE_SESSION);
    }
    
    public static ProjectFeature getProjectFeature(WebRequest webRequest) {
        return (ProjectFeature) webRequest.getAttribute("feature", WebRequest.SCOPE_SESSION);
    }

    public static String getRedirectPath(WebRequest webRequest) {
        Project project = getProject(webRequest);
        ProjectFeature feature = getProjectFeature(webRequest);
        return getRedirectPath(project, feature);
    }

    public static String getRedirectPath(Project project, ProjectFeature feature) {
        return "redirect:/" + project.getCode() + "/" + feature.getCode();
    }
    
}
