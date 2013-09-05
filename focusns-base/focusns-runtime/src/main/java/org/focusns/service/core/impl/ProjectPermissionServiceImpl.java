package org.focusns.service.core.impl;

import org.focusns.dao.core.ProjectAuthorityDao;
import org.focusns.dao.core.ProjectDao;
import org.focusns.dao.core.ProjectPermissionDao;
import org.focusns.dao.core.ProjectRoleDao;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectAuthority;
import org.focusns.model.core.ProjectPermission;
import org.focusns.model.core.ProjectRole;
import org.focusns.service.core.ProjectPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProjectPermissionServiceImpl implements ProjectPermissionService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectRoleDao projectRoleDao;
    @Autowired
    private ProjectAuthorityDao projectAuthorityDao;
    @Autowired
    private ProjectPermissionDao projectPermissionDao;

    @Override
    public ProjectPermission getProjectPermissioin(long permissionId) {
        ProjectPermission permission = projectPermissionDao.select(permissionId);
        return fillProjectPermission(permission);
    }

    @Override
    public void createProjectPermission(ProjectPermission permission) {
        projectPermissionDao.insert(permission);
        fillProjectPermission(permission);
    }

    @Override
    public void modifyProjectPermission(ProjectPermission permission) {
        projectPermissionDao.update(permission);
        fillProjectPermission(permission);
    }

    @Override
    public void removeProjectPermission(ProjectPermission permission) {
        projectPermissionDao.update(permission);
        fillProjectPermission(permission);
    }

    @Override
    public List<ProjectPermission> listProjectPermissions(long projectId) {
        List<ProjectPermission> permissions = projectPermissionDao.selectByProjectId(projectId);
        // TODO performance tuning
        for(ProjectPermission permission : permissions) {
            fillProjectPermission(permission);
        }
        return permissions;
    }

    @Override
    public Map<ProjectRole, List<ProjectPermission>> listProjectPermissionsAsMap(long projectId) {
        List<ProjectPermission> projectPermissions = listProjectPermissions(projectId);
        //
        ProjectRole prevProjectRole = null;
        List<ProjectPermission> projectPermissionList = new ArrayList<ProjectPermission>();
        Map<ProjectRole, List<ProjectPermission>> projectRolePermissionMap = new LinkedHashMap<ProjectRole, List<ProjectPermission>>();
        for(ProjectPermission projectPermission : projectPermissions) {
            //
            if(prevProjectRole!=null && prevProjectRole.getId()!=projectPermission.getProjectRoleId()) {
                projectRolePermissionMap.put(prevProjectRole, projectPermissionList);
                projectPermissionList = new ArrayList<ProjectPermission>();
            }
            //
            projectPermissionList.add(projectPermission);
            prevProjectRole = projectPermission.getProjectRole();
        }
        //
        if(prevProjectRole!=null && projectPermissionList.isEmpty()==false) {
            projectRolePermissionMap.put(prevProjectRole, projectPermissionList);
        }
        //
        return projectRolePermissionMap;
    }

    protected ProjectPermission fillProjectPermission(ProjectPermission permission) {
        if(permission==null) {
            return permission;
        }
        //
        if(permission.getProject()==null && permission.getProjectId()>0) {
            Project project = projectDao.select(permission.getProjectId());
            permission.setProject(project);
        }
        if(permission.getProjectRole()==null && permission.getProjectRoleId()>0) {
            ProjectRole projectRole = projectRoleDao.select(permission.getProjectRoleId());
            permission.setProjectRole(projectRole);
        }
        if(permission.getProjectAuthority()==null && permission.getProjectAuthorityId()>0) {
            ProjectAuthority projectAuthority = projectAuthorityDao.select(permission.getProjectAuthorityId());
            permission.setProjectAuthority(projectAuthority);
        }
        //
        return permission;
    }

}
