package org.focusns.web.modules.profile;

import java.io.File;
import java.io.IOException;

import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectUserService;
import org.focusns.web.helper.ApplicationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/project/user")
public class ProjectUserController {

    @Autowired
    private ProjectUserService projectUserService;

    @RequestMapping("{userId}/logo")
    public @ResponseBody
    byte[] linkLogo(@PathVariable long userId) throws IOException {
        //
        ProjectUser user = projectUserService.getUser(userId);
        File target = ApplicationHelper.getInstance().getProjectLogo(user.getProjectId());
        return FileCopyUtils.copyToByteArray(target);
    }

}
