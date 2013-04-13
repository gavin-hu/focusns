package org.focusns.service.core;

/*
 * #%L
 * FocusSNS Runtime
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

import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectHistory;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.AbstractServiceTest;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Ignore
public class ProjectHistoryServiceTest extends AbstractServiceTest {

    @Autowired
    private ProjectUserService projectUserService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectHistoryService projectHistoryService;

    @Test
    public void testCreateProjectHistroy() {
        Project project = projectService.getProject("admin");
        Assert.assertNotNull(project);
        //
        ProjectUser user = projectUserService.getUser("admin");
        Assert.assertNotNull(user);
        //
        ProjectHistory history = new ProjectHistory();
        history.setContent("This is project history!");
        history.setCreateAt(new Date());
        history.setCreateById(user.getId());
        history.setTargetType("project");
        history.setTargetId(1);
        history.setParentId(1);
        history.setProjectId(project.getId());
        //
        projectHistoryService.createProjectHistory(history);
    }

    @Test
    public void testFetchPage() {
        Page<ProjectHistory> page = new Page<ProjectHistory>(10);
        page = projectHistoryService.fetchPage(page, 2);
        System.out.println(page.getTotalCount());
    }

}
