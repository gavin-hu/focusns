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

import org.focusns.dao.core.ProjectAuthorityDao;
import org.focusns.model.core.ProjectAuthority;
import org.focusns.service.core.ProjectAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectAuthorityServiceImpl implements ProjectAuthorityService {

    @Autowired
    private ProjectAuthorityDao projectAuthorityDao;

    public ProjectAuthority getProjectAuthority(long authorityId) {
        return projectAuthorityDao.select(authorityId);
    }

    public void createProjectAuthority(ProjectAuthority authority) {
        this.projectAuthorityDao.insert(authority);
    }

    public void modifyProjectAuthority(ProjectAuthority authority) {
        this.projectAuthorityDao.update(authority);
    }

    public void removeProjectAuthority(ProjectAuthority authority) {
        this.projectAuthorityDao.delete(authority.getId());
    }
}
