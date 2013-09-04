package org.focusns.service.core;

import org.focusns.model.common.Page;
import org.focusns.model.core.ProjectMember;

public interface ProjectMemberService {

    ProjectMember getProjectMember(long memberId);

    void createProjectMember(ProjectMember member);

    void modifyProjectMember(ProjectMember member);

    void removeProjectMember(ProjectMember member);

    /**
     * 分页返回给定 空间ID 的成员
     * @param page
     * @param projectId
     * @return
     */
    Page<ProjectMember> fetchPage(Page<ProjectMember> page, long projectId);

}
