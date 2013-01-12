/*
 * Copyright (C) 2012 FocusSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.focusns.service.core;

import junit.framework.Assert;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectHistroy;
import org.focusns.model.core.User;
import org.focusns.service.AbstractServiceTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Ignore
public class ProjectHistroyServiceTest extends AbstractServiceTest {
    
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectHistroyService projectHistroyService;
    
    @Test
    public void testCreateProjectHistroy() {
        Project project = projectService.getProject("focusns");
        Assert.assertNotNull(project);
        //
        User user = userService.getUser("admin");
        Assert.assertNotNull(user);
        //
        ProjectHistroy histroy = new ProjectHistroy();
        histroy.setContent("This is project histroy!");
        histroy.setCreateAt(new Date());
        histroy.setCreateById(user.getId());
        histroy.setTargetType("project");
        histroy.setTargetId(1);
        histroy.setProjectId(project.getId());
        //
        projectHistroyService.createProjectHistroy(histroy);
    }
    
}
