

package org.focusns.web.widget.filter;

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
import org.focusns.model.core.ProjectUser;
import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;
import org.focusns.web.widget.annotation.Widget;
import org.focusns.web.widget.config.WidgetConfig;
import org.focusns.web.widget.engine.WidgetFilter;
import org.springframework.util.Assert;

public class MyProjectFilter implements WidgetFilter {
    @Override
    public boolean doFilter(WidgetRequest request, WidgetResponse response, WidgetConfig widgetConfig) throws Exception {
        //
        Project project = request.getRequestAttribute("project");
        ProjectUser projectUser = request.getSessionAttribute("user");
        Assert.notNull(project);
        //
        return projectUser!=null && projectUser.getId()==project.getCreateById();
    }
}
