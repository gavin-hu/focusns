package org.focusns.web.modules.admin;

import org.focusns.common.web.widget.annotation.bind.WidgetAttribute;
import org.focusns.common.web.widget.mvc.support.Navigator;
import org.focusns.model.core.Project;
import org.focusns.service.core.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
public class AdminProjectWidget {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value="/project-edit")
    public String doEdit(@WidgetAttribute Project project) {
        return "modules/admin/project-edit";
    }

    @RequestMapping(value="/project-modify", method = RequestMethod.POST)
    public void doModify(Project project) {
        //
        projectService.modifyProject(project);
        //
        Navigator.get().withAttribute("project", project).navigateTo("project-modified");
    }

}
