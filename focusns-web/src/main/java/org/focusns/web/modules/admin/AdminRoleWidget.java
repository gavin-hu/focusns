package org.focusns.web.modules.admin;


import org.focusns.common.web.widget.annotation.bind.WidgetAttribute;
import org.focusns.common.web.widget.mvc.support.Navigator;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectRole;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminRoleWidget {

    @Autowired
    private ProjectRoleService projectRoleService;

    @RequestMapping(value="/role-edit")
    public String doEdit(@RequestParam(required = false) Long roleId, @WidgetAttribute Project project,
                         @WidgetAttribute ProjectUser projectUser, Model model) {
        //
        ProjectRole projectRole = new ProjectRole();
        if(roleId==null) {
            projectRole.setProjectId(project.getId());
        } else {
            projectRole = projectRoleService.getProjectRole(roleId);
        }
        //
        List<ProjectRole> projectRoles = projectRoleService.listProjectRoles(project.getId());
        //
        model.addAttribute("projectRole", projectRole);
        model.addAttribute("projectRoles", projectRoles);
        //
        return "modules/admin/role-edit";
    }

    @RequestMapping(value={ "/role-create", "/role-modify" })
    public void doModify(ProjectRole projectRole) {
        //
        Navigator.get().withAttribute("projectRole", projectRole);
        if(projectRole.getId()>0) {
            projectRoleService.modifyProjectRole(projectRole);
            Navigator.get().navigateTo("role-modified");
        } else {
            projectRoleService.createProjectRole(projectRole);
            Navigator.get().navigateTo("role-created");
        }
    }

    @RequestMapping(value="/role-remove")
    public void doRemove(@RequestParam Long id) {
        ProjectRole projectRole = projectRoleService.getProjectRole(id);
        projectRoleService.removeProjectRole(projectRole);
        Navigator.get().withAttribute("projectRole", projectRole).navigateTo("role-removed");
    }

}
