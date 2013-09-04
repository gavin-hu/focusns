package org.focusns.dao.core;

import org.focusns.dao.AbstractDaoTest;
import org.focusns.model.core.ProjectAuthority;
import org.focusns.model.core.ProjectPermission;
import org.focusns.model.core.ProjectRole;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Ignore
public class ProjectPermissionDaoTest extends AbstractDaoTest {

    @Autowired
    private ProjectRoleDao projectRoleDao;
    @Autowired
    private ProjectAuthorityDao projectAuthorityDao;
    @Autowired
    private ProjectPermissionDao projectPermissionDao;

    @Test
    public void testInsert() {
        //
        ProjectAuthority projectAuthority = new ProjectAuthority();
        projectAuthority.setCode("project-admin-view");
        projectAuthority.setDescription("管理模块查看权力");
        //
        projectAuthorityDao.insert(projectAuthority);
        //
        ProjectRole projectRole  = new ProjectRole();
        projectRole.setProjectId(1);
        projectRole.setLabel("管理员");
        projectRole.setLevel(5);
        //
        projectRoleDao.insert(projectRole);
        //
        ProjectPermission projectPermission = new ProjectPermission();
        projectPermission.setProjectId(1);
        projectPermission.setProjectRoleId(projectRole.getId());
        projectPermission.setProjectAuthorityId(projectAuthority.getId());
        //
        projectPermissionDao.insert(projectPermission);
    }

    @Test
    public void testSelect() {
        //
        List<ProjectPermission> projectPermissions = projectPermissionDao.selectByProjectId(1);
        System.out.println(projectPermissions.size());
    }

}
