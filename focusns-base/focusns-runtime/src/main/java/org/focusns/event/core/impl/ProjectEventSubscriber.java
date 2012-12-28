/*
 * Copyright (C) 2012 FocusSNS.
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
package org.focusns.event.core.impl;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.focusns.common.event.Event;
import org.focusns.common.event.annotation.Subscriber;
import org.focusns.common.event.annotation.Subscriber.OnEvent;
import org.focusns.common.event.support.EventContext;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectHistroy;
import org.focusns.service.core.ProjectHistroyService;
import org.springframework.beans.factory.annotation.Autowired;

@Subscriber
public class ProjectEventSubscriber {

    private Log log = LogFactory.getLog(ProjectEventSubscriber.class);
    
    @Autowired
    private ProjectHistroyService projectHistroyService;
    
    @OnEvent(Event.MODIFIED)
    public void onProjectModified(EventContext taskContext) {
        log.info("event triggered!");
        //
        Project project = taskContext.getParameter("project", Project.class);
        //
        ProjectHistroy projectHistroy = new ProjectHistroy();
        projectHistroy.setCreateAt(new Date());
        projectHistroy.setContent("Project modified!");
        projectHistroy.setTargetId(project.getId());
        projectHistroy.setTargetType(Project.class.getName());
        projectHistroy.setProjectId(project.getId());
        projectHistroy.setCreateById(project.getModifyById());
        //
        projectHistroyService.createProjectHistroy(projectHistroy);
    }
    
}
