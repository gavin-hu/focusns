
package org.focusns.web.modules.profile;

import org.focusns.web.helper.ApplicationHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @RequestMapping("{projectId}/logo")
    public @ResponseBody byte[] linkLogo(@PathVariable long projectId) throws IOException {
        //
        File target = ApplicationHelper.getInstance().getProjectLogo(projectId);
        return FileCopyUtils.copyToByteArray(target);
    }
    
}