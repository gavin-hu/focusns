package org.focusns.service.team;

/*
 * #%L
 * FocusSNS Service
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

import org.focusns.model.common.Page;
import org.focusns.model.team.TeamMember;

public interface TeamMemberService {

    /**
     * 根据成员 ID 获取成员
     * @param memberId
     * @return
     */
    TeamMember getTeamMember(long memberId);

    /**
     * 创建新成员
     * @param member
     */
    void createTeamMember(TeamMember member);

    /**
     * 修改成员
     * @param member
     */
    void modifyTeamMember(TeamMember member);

    /**
     * 移除成员
     * @param member
     */
    void removeTeamMember(TeamMember member);

    /**
     * 获取成员列表
     * @param page
     * @param projectId
     * @return
     */
    Page<TeamMember> fetchPage(Page<TeamMember> page, long projectId);

    /**
     * 获取成员列表
     * @param page
     * @param projectId
     * @param roleId
     * @return
     */
    Page<TeamMember> fetchPage(Page<TeamMember> page, long projectId, long roleId);

    /**
     * 获取潜在在成员列表
     * @param page
     * @return
     */
    Page<TeamMember> fetchPagePotentially(Page<TeamMember> page);

}
