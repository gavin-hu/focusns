package org.focusns.service.core.impl;

import org.focusns.model.common.Page;
import org.focusns.model.core.ProjectMember;
import org.focusns.service.core.ProjectMemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    @Override
    public ProjectMember getProjectMember(long memberId) {
        return null;
    }

    @Override
    public void createProjectMember(ProjectMember member) {

    }

    @Override
    public void modifyProjectMember(ProjectMember member) {

    }

    @Override
    public void removeProjectMember(ProjectMember member) {

    }

    @Override
    public ProjectMember getProjectMember(long projectId, long projectUserId) {
        return null;
    }

    @Override
    public Page<ProjectMember> fetchPage(Page<ProjectMember> page, long projectId) {
        return null;
    }
}
