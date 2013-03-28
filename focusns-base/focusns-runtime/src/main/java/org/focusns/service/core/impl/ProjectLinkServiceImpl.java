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
import org.focusns.dao.core.ProjectLinkDao;
import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectLink;
import org.focusns.service.core.ProjectLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectLinkServiceImpl implements ProjectLinkService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectLinkDao linkDao;

    
    public ProjectLink getProjectLink(long id) {
        return linkDao.select(id);
    }

    public void createProjectLink(ProjectLink link) {
        ProjectLink dbLink = linkDao.selectByFromAndToProjectId(link.getFromProjectId(), link.getToProjectId());
        if(dbLink==null) {
            linkDao.insert(link);
        } else {
            throw new RuntimeException("The link already exist!");
        }
    }

    public void modifyProjectLink(ProjectLink link) {
        linkDao.update(link);
    }

    public void removeProjectLink(ProjectLink link) {
        linkDao.delete(link.getId());
    }

    public void removeProjectLink(long fromProjectId, long toProjectId) {
        linkDao.deleteByFromAndToProjectId(fromProjectId, toProjectId);
    }

    public ProjectLink getProjectLink(long fromProjectId, long toProjectId) {
        return linkDao.selectByFromAndToProjectId(fromProjectId, toProjectId);
    }

    public Page<ProjectLink> fetchPageByToProjectId(Page<ProjectLink> page, long toProjectId, String category) {
        //
        page = linkDao.fetchByToProjectId(page, toProjectId, category, null);
        //
        Project toProject = projectDao.select(toProjectId);
        for(ProjectLink projectLink : page.getResults()) {
            projectLink.setToProject(toProject);
        }
        return page;
    }

    public Page<ProjectLink> fetchPageByFromProjectId(Page<ProjectLink> page, long fromProjectId, String category) {
        //
        page = linkDao.fetchByFromProjectId(page, fromProjectId, category, null);
        //
        Project fromProject = projectDao.select(fromProjectId);
        for(ProjectLink projectLink : page.getResults()) {
            projectLink.setFromProject(fromProject);
        }
        return page;
    }

}
