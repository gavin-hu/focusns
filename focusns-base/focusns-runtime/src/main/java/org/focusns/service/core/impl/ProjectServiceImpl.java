package org.focusns.service.core.impl;

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

import org.focusns.dao.core.ProjectDao;
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectCategory;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectCategoryService;
import org.focusns.service.core.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectUserDao projectUserDao;
    @Autowired
    private ProjectCategoryService projectCategoryService;

    public Project getProject(long id) {
        return projectDao.select(id);
    }

    public Project getProject(String code) {
        Project project = projectDao.selectByCode(code);
        ProjectUser createBy = projectUserDao.select(project.getCreatedById());
        ProjectUser modifiedBy = projectUserDao.select(project.getModifiedById());
        ProjectCategory projectCategory = projectCategoryService.getCategory(project.getCategoryId());
        project.setCreatedBy(createBy);
        project.setModifiedBy(modifiedBy);
        project.setCategory(projectCategory);
        //
        return project;
    }

    public void createProject(Project project) {
        projectDao.insert(project);
    }

    public void removeProject(Project project) {
        projectDao.delete(project.getId());
    }

    public void modifyProject(Project project) {
        projectDao.update(project);
    }

}
