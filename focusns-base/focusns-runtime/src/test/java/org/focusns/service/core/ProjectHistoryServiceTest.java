package org.focusns.service.core;

import java.util.Date;

import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectHistory;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.AbstractServiceTest;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
