
package org.focusns.web.helper;

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
