
package org.focusns.web.modules.profile;

import org.focusns.model.core.ProjectHistory;
import org.focusns.service.core.ProjectHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project/history")
public class ProjectHistoryController {
    
    @Autowired
    private ProjectHistoryService projectHistoryService;
    
    @RequestMapping("/create")
    public void createHistory(ProjectHistory history) {
        //
        projectHistoryService.createProjectHistory(history);
    }

    @RequestMapping("/remove")
    public void removeHistory(ProjectHistory history) {
        //
        projectHistoryService.removeProjectHistory(history);
    }
    
}
