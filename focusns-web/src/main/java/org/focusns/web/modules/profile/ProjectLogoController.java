
package org.focusns.web.modules.profile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectLogoController {

   /* @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectLogoService projectLogoService;
    
    @RequestMapping("/logo")
    public @ResponseBody byte[] linkLogo(@PathVariable long logoId) throws IOException {
        //
        ProjectLogo logo = projectLogoService.getProjectLogo(logoId);
        //
        File target = ApplicationHelper.getInstance().getProjectLogo(logo);
        return FileCopyUtils.copyToByteArray(target);
    }*/

}
