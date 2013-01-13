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
import org.focusns.model.core.ProjectLink;
import org.focusns.service.core.ProjectLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectLinkServiceImpl implements ProjectLinkService {

    @Autowired
    private ProjectLinkDao linkDao;
    
    public ProjectLink getProjectLink(long id) {
        return linkDao.select(id);
    }

    public void createProjectLink(ProjectLink link) {
        linkDao.insert(link);
    }

    public void modifyProjectLink(ProjectLink link) {
        linkDao.update(link);
    }

    public void removeProjectLink(ProjectLink link) {
        linkDao.delete(link.getId());
    }
    
}
