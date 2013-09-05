package org.focusns.dao.core;

import org.focusns.dao.AbstractDaoTest;
import org.focusns.model.core.ProjectMember;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Ignore
public class ProjectMemberDaoTest extends AbstractDaoTest {

    @Autowired
    private ProjectMemberDao projectMemberDao;

    @Test
    public void testInsert() {
        ProjectMember projectMember = new ProjectMember();
        projectMember.setProjectId(1);
        projectMember.setProjectUserId(1);
        projectMember.setProjectRoleId(1);
        projectMember.setCreatedById(1);
        projectMember.setModifiedById(1);
        projectMember.setCreatedAt(new Date());
        projectMember.setModifiedAt(new Date());
        //
        projectMemberDao.insert(projectMember);
    }

}
