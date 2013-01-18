/*
 * Copyright (C) 2011-2013 FocusSNS.
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
package org.focusns.service.core.impl;

import org.focusns.dao.core.ProjectDao;
import org.focusns.dao.core.ProjectHistroyDao;
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.model.common.Page;
import org.focusns.model.core.ProjectHistroy;
import org.focusns.service.core.ProjectHistroyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProjectHistroyServiceImpl implements ProjectHistroyService {

    @Autowired
    private ProjectUserDao projectUserDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectHistroyDao projectHistroyDao;
    
    public void createProjectHistroy(ProjectHistroy histroy) {
        //
        Date now = new Date();
        histroy.setCreateAt(now);
        //
        projectHistroyDao.insert(histroy);
    }

    public void modifyProjectHistroy(ProjectHistroy histroy) {
        projectHistroyDao.update(histroy);
    }

    public void removeProjectHistroy(ProjectHistroy histroy) {
        projectHistroyDao.delete(histroy.getId());
    }

    public Page<ProjectHistroy> fetchPage(Page<ProjectHistroy> page, long projectId) {
        page = projectHistroyDao.fetchByProjectId(page, projectId);
        //
        for(ProjectHistroy histroy : page.getResults()) {
            List<ProjectHistroy> children = projectHistroyDao
                    .selectByParentId(histroy.getId());
            histroy.setChildren(children);
        }
        //
        return page;
    }
    
}
