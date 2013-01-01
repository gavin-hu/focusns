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
package org.focusns.web.utils;

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
    
}
