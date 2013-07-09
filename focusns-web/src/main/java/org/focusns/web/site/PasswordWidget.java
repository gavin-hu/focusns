package org.focusns.web.site;

import org.focusns.common.mail.MailService;
import org.focusns.common.web.widget.stereotype.Widget;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Widget
@RequestMapping("site")
public class PasswordWidget {

    @Autowired
    private MailService mailService;
    @Autowired
    private ProjectUserService projectUserService;

    @RequestMapping(value="password-confirm")
    public String confirm(@RequestParam Long userId, Model model) {
        //
        ProjectUser projectUser = projectUserService.getProjectUser(userId);
        model.addAttribute("projectUser", projectUser);
        //
        Map<String, Object> ctx = new HashMap<String, Object>();
        ctx.put("project", projectUser);
        mailService.send("password-confirm", ctx);
        //
        return "site/signup-confirm";
    }

}
