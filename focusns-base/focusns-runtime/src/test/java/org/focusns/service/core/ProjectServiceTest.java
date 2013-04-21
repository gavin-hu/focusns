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

import java.util.Date;

import org.focusns.dao.core.ProjectUserDao;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectCategory;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.AbstractServiceTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class ProjectServiceTest extends AbstractServiceTest {

    @Autowired
    private ProjectUserDao projectUserDao;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectCategoryService projectCategoryService;

    @Test
    public void testCreateProject() {
        ProjectCategory category = new ProjectCategory();
        category.setCode("People");
        category.setLabel("人员");
        projectCategoryService.createCategory(category);
        //
        ProjectUser user = new ProjectUser();
        user.setUsername("haozhonghu");
        user.setPassword("123456");
        user.setEmail("haozhonghu@hotmail.com");
        projectUserDao.insert(user);
        //
        Date now = new Date();
        Project project = new Project();
        project.setCode("gavin");
        project.setTitle("Gavin Hu");
        project.setDescription("This is gavin's profile!");
        //
        project.setCreatedAt(now);
        project.setModifiedAt(now);
        project.setCreatedById(user.getId());
        project.setModifiedById(user.getId());
        project.setCategoryId(category.getId());
        //
        projectService.createProject(project);
    }

    @Test
    public void testDeleteProject() {
        Project project = projectService.getProject("gavin");
        //
        projectService.removeProject(project);
        //
        projectUserDao.delete(project.getCreatedById());
        //
        ProjectCategory category = projectCategoryService.getCategory(project.getCategoryId());
        projectCategoryService.removeCategory(category);

    }

}
