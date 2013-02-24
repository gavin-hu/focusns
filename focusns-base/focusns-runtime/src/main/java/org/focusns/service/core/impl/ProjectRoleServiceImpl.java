package org.focusns.service.core.impl;

import org.focusns.dao.core.ProjectRoleDao;
import org.focusns.model.core.ProjectRole;
import org.focusns.service.core.ProjectRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectRoleServiceImpl implements ProjectRoleService {

    @Autowired
    private ProjectRoleDao projectRoleDao;

    public ProjectRole getProjectRole(long roleId) {
        return projectRoleDao.select(roleId);
    }

    public void createProjectRole(ProjectRole role) {
        this.projectRoleDao.insert(role);
    }

    public void modifyProjectRole(ProjectRole role) {
        this.projectRoleDao.update(role);
    }

    public void removeProjectRole(ProjectRole role) {
        this.projectRoleDao.delete(role.getId());
    }

    public void authorizeAuthority(long projectId, long roleId, long authorityId) {
        this.projectRoleDao.insertAuthority(projectId, roleId, authorityId);
    }

    public void deauthorizeAuthority(long projectId, long roleId, long authorityId) {
        this.projectRoleDao.deleteAuthority(projectId, roleId, authorityId);
    }
}
