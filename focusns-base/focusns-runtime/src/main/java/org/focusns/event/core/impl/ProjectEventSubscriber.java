package org.focusns.event.core.impl;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.focusns.common.event.Event;
import org.focusns.common.event.annotation.Subscriber;
import org.focusns.common.event.annotation.Subscriber.OnEvent;
import org.focusns.common.event.support.EventContext;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectHistory;
import org.focusns.service.core.ProjectHistoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Subscriber
public class ProjectEventSubscriber {

    private Log log = LogFactory.getLog(ProjectEventSubscriber.class);
    
    @Autowired
    private ProjectHistoryService projectHistoryService;
    
    @OnEvent(Event.MODIFIED)
    public void onProjectModified(EventContext taskContext) {
        log.info("event triggered!");
        //
        Project project = taskContext.getParameter("project", Project.class);
        //
        ProjectHistory projectHistory = new ProjectHistory();
        projectHistory.setCreateAt(new Date());
        projectHistory.setContent("Project modified!");
        projectHistory.setTargetId(project.getId());
        projectHistory.setTargetType(Project.class.getName());
        projectHistory.setProjectId(project.getId());
        projectHistory.setCreateById(project.getModifyById());
        //
        projectHistoryService.createProjectHistory(projectHistory);
    }
    
}
