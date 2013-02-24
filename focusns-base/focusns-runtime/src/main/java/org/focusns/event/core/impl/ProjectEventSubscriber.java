package org.focusns.event.core.impl;

import java.util.Date;

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
