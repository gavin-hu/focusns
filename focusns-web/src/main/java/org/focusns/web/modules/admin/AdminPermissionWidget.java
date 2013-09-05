package org.focusns.web.modules.admin;

import org.focusns.common.web.widget.annotation.bind.WidgetAttribute;
import org.focusns.common.web.widget.mvc.support.Navigator;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectAuthority;
import org.focusns.model.core.ProjectPermission;
import org.focusns.model.core.ProjectRole;
import org.focusns.service.core.ProjectAuthorityService;
import org.focusns.service.core.ProjectPermissionService;
import org.focusns.service.core.ProjectRoleService;
import org.focusns.service.core.ProjectService;
import org.focusns.web.form.core.ProjectPermissionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/admin")
public class AdminPermissionWidget {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectRoleService projectRoleService;
    @Autowired
    private ProjectAuthorityService projectAuthorityService;
    @Autowired
    private ProjectPermissionService projectPermissionService;

    @RequestMapping(value = "/permission-edit", method = GET)
    public String doEdit(@WidgetAttribute Project project, Model model) {
        //
        List<ProjectRole> projectRoles = projectRoleService.listProjectRoles(project.getId());
        model.addAttribute("projectRoles", projectRoles);
        //
        List<ProjectAuthority> projectAuthorities = projectAuthorityService.listProjectAuthorities();
        model.addAttribute("projectAuthorities", projectAuthorities);
        //
        Map<ProjectRole, List<ProjectPermission>> projectRolePermissionMap =
                projectPermissionService.listProjectPermissionsAsMap(project.getId());
        model.addAttribute("projectRolePermissionMap", projectRolePermissionMap);
        //
        return "modules/admin/permission-edit";
    }

    @RequestMapping(value = {"/permission-create", "/permission-modify"}, method = POST)
    public void doModify(@RequestParam Long projectId, ProjectPermissionForm form) {
        //
        for(ProjectPermission projectPermission : form.getProjectPermissions()) {
            //
            if(projectPermission.getId()>0) {
               projectPermissionService.modifyProjectPermission(projectPermission);
            } else {
               projectPermissionService.createProjectPermission(projectPermission);
            }
        }
        //
        Project project = projectService.getProject(projectId);
        Navigator.get().withAttribute("project", project).navigateTo("permissions-modified");
    }

}
