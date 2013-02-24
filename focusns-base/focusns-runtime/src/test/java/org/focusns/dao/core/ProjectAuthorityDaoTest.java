package org.focusns.dao.core;

import org.focusns.dao.AbstractDaoTest;
import org.focusns.model.core.ProjectAuthority;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class ProjectAuthorityDaoTest extends AbstractDaoTest {

    @Autowired
    private ProjectAuthorityDao projectAuthorityDao;

    @Test
    public void testInsert() {
         ProjectAuthority authority = new ProjectAuthority();
        authority.setCode("project-profile-view");
        authority.setDescription("查看主页模块");
        //
        this.projectAuthorityDao.insert(authority);
    }
}
