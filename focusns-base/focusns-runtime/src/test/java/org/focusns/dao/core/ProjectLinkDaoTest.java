package org.focusns.dao.core;

import org.focusns.dao.AbstractDaoTest;
import org.focusns.model.core.ProjectLink;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class ProjectLinkDaoTest extends AbstractDaoTest {

    @Autowired
    private ProjectLinkDao projectLinkDao;

    @Test
    public void testInsert() {
        ProjectLink link = new ProjectLink();
        link.setFromProjectId(1);
        link.setToProjectId(1);
        //
        projectLinkDao.insert(link);
    }
}
