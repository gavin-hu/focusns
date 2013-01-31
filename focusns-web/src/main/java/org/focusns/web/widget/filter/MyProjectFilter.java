

package org.focusns.web.widget.filter;

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
