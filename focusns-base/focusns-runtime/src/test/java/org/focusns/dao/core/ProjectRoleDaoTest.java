package org.focusns.dao.core;

import org.focusns.dao.AbstractDaoTest;
import org.focusns.model.core.ProjectRole;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class ProjectRoleDaoTest extends AbstractDaoTest {

    @Autowired
    private ProjectRoleDao projectRoleDao;

    @Test
    public void testInsert() {
        ProjectRole role = new ProjectRole();
        role.setLabel("管理员");
        role.setLevel(0);
        role.setProjectId(1);
        role.setCategoryId(1);
        //
        this.projectRoleDao.insert(role);
    }

    @Test
    public void testInsertAuthority() {
        this.projectRoleDao.insertAuthority(1, 1, 1);
    }

    @Test
    public void testDeleteAuthority() {
        this.projectRoleDao.deleteAuthority(1, 1, 1);
    }
}
