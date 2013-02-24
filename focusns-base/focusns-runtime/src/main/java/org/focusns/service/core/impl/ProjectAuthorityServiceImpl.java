package org.focusns.service.core.impl;

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
