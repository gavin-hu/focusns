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

import org.focusns.dao.core.ProjectLinkDao;
import org.focusns.model.common.Page;
import org.focusns.model.core.ProjectLink;
import org.focusns.service.core.ProjectLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectLinkServiceImpl implements ProjectLinkService {

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

    @Override
    public ProjectLink getProjectLink(long fromProjectId, long toProjectId) {
        return linkDao.selectByFromAndToProjectId(fromProjectId, toProjectId);
    }

    @Override
    public Page<ProjectLink> fetchPageByToProjectId(Page<ProjectLink> page, long toProjectId) {
        return linkDao.fetchByToProjectId(page, toProjectId, null);
    }

    @Override
    public Page<ProjectLink> fetchPageByFromProjectId(Page<ProjectLink> page, long fromProjectId) {
        return linkDao.fetchByFromProjectId(page, fromProjectId, null);
    }


}
