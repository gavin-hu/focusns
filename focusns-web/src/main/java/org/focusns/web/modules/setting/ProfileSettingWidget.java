
package org.focusns.web.modules.setting;

import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectAttribute;
import org.focusns.web.helper.ApplicationHelper;
import org.focusns.service.core.ProjectAttributeService;
import org.focusns.web.widget.annotation.Bind;
import org.focusns.web.widget.annotation.Widget;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;
import java.util.Map;

@Widget
public class ProfileSettingWidget {

    @Autowired
    private ProjectAttributeService attributeService;
    
    public String editLogo(Map<String, Object> model,
            @Bind(value="id", scope = Bind.Scope.SESSION) String sessionId,
            @Bind(value="project", scope= Bind.Scope.REQUEST) Project project) {
        //
        File tmpLogo = ApplicationHelper.getInstance().getTmpProjectLogo(sessionId);
        model.put("hasTmpLogo", tmpLogo.canRead());
        //
        File targetLogo = ApplicationHelper.getInstance().getProjectLogo(project.getId());
        model.put("hasLogo", targetLogo.canRead());
        //
        return "modules/setting/profile/logo-edit";
    }
    
    public String editProject(@Bind(value="project", scope = Bind.Scope.REQUEST) Project project) {
        return "modules/setting/profile/project-edit";
    }
    
    public String editAttribute(Map<String, Object> model,
            @Bind(value="project", scope = Bind.Scope.REQUEST) Project project) {
        //
        List<ProjectAttribute> attributes = attributeService.getProjectAttributes(project.getId());
        model.put("attributes", attributes);
        //
        return "modules/setting/profile/attribute-edit";
    }
    
}
