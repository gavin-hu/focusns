package org.focusns.web.modules.team;

/*
 * #%L
 * FocusSNS Web
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

import org.focusns.common.web.widget.annotation.bind.WidgetAttribute;
import org.focusns.common.web.widget.annotation.bind.WidgetPref;
import org.focusns.common.web.widget.mvc.support.Navigator;
import org.focusns.common.web.widget.stereotype.Widget;
import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectLink;
import org.focusns.model.core.ProjectRole;
import org.focusns.model.team.TeamMember;
import org.focusns.service.core.ProjectLinkService;
import org.focusns.service.core.ProjectRoleService;
import org.focusns.service.team.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Widget
@RequestMapping("/team")
public class TeamMemberWidget {

    @Autowired
    private TeamMemberService teamMemberService;
    @Autowired
    private ProjectRoleService projectRoleService;
    @Autowired
    private ProjectLinkService projectLinkService;

    @RequestMapping(value="/member-edit", method = GET)
    public String doEdit(@RequestParam(defaultValue = "users") String tab, @RequestParam(defaultValue = "1") Integer pageNo,
                         @WidgetAttribute Project project, @WidgetPref(defaultValue = "12") Integer pageSize, Model model) {
        //
        if("users".equals(tab)) {
            Page<TeamMember> teamMemberPage = new Page<TeamMember>(pageSize);
            teamMemberPage.setPageNo(pageNo);
            teamMemberPage = teamMemberService.fetchPagePotentially(teamMemberPage);
            model.addAttribute("teamMemberPage", teamMemberPage);
        }
        if("link1".equals(tab)) {
            Page<ProjectLink> linkPage = new Page<ProjectLink>(pageSize);
            linkPage.setPageNo(pageNo);
            linkPage = projectLinkService.fetchPageByFromProjectId(
                    linkPage, project.getId(), project.getCategory().getCode());
            model.addAttribute("linkPage", linkPage);
        }
        if("link2".equals(tab)) {
            Page<ProjectLink> reversedLinkPage = new Page<ProjectLink>(pageSize);
            reversedLinkPage.setPageNo(pageNo);
            reversedLinkPage = projectLinkService.fetchPageByToProjectId(
                    reversedLinkPage, project.getId(), project.getCategory().getCode());
            model.addAttribute("reversedLinkPage", reversedLinkPage);
        }
        //
        List<ProjectRole> projectRoles = projectRoleService.listProjectRoles(project.getId());
        model.addAttribute("projectRoles", projectRoles);
        //
        return "modules/team/member-edit";
    }

    @RequestMapping(value="/member-list", method = GET)
    public String doList(@RequestParam(defaultValue = "0") Long roleId, @RequestParam(defaultValue = "1") Integer pageNo,
                         @WidgetAttribute Project project, @WidgetPref(defaultValue = "12") Integer pageSize, Model model) {
        //
        List<ProjectRole> projectRoles = projectRoleService.listProjectRoles(project.getId());
        model.addAttribute("projectRoles", projectRoles);
        //
        Page<TeamMember> teamMemberPage = new Page<TeamMember>(pageSize);
        teamMemberPage.setPageNo(pageNo);
        teamMemberPage = teamMemberService.fetchPage(teamMemberPage, project.getId(), roleId);
        model.addAttribute("teamMemberPage", teamMemberPage);
        //
        return "modules/team/member-list";
    }

    @RequestMapping(value = {"/member-create", "member-modify"}, method = {GET, POST})
    public void doModify(TeamMember teamMember) {
        //
        Navigator.get().withAttribute("teamMember", teamMember);
        if(teamMember.getId()>0) {
            teamMemberService.modifyTeamMember(teamMember);
            Navigator.get().navigateTo("member-modified");
        } else {
            teamMemberService.createTeamMember(teamMember);
            Navigator.get().navigateTo("member-created");
        }
    }

}
