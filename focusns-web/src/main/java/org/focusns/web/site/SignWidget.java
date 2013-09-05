package org.focusns.web.site;

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

import org.focusns.common.mail.MailService;
import org.focusns.common.web.widget.mvc.support.Navigator;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectUserService;
import org.focusns.web.widget.Constraint;
import org.focusns.web.widget.Constraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("site")
public class SignWidget {

    @Autowired
    private MailService mailService;
    @Autowired
    private ProjectUserService projectUserService;

    @RequestMapping(value="signin")
    @Constraints({ Constraint.PROJECT_USER_IS_NULL })
    public String signIn() {
        return "site/signin-edit";
    }

    @RequestMapping(value="signup")
    public String signUp() {
        return "site/signup-edit";
    }

    @RequestMapping(value="signup", method = RequestMethod.POST)
    public void doSignUp(@Valid ProjectUser projectUser) {
        //
        ProjectUser user = projectUserService.getProjectUser(projectUser.getEmail());
        if(user!=null) {

        } else {
            projectUserService.createProjectUser(projectUser);
        }
        //
        /*Map<String, Object> ctx = new HashMap<String, Object>();
        ctx.put("projectUser", projectUser);
        mailService.send("signup-confirm", ctx);*/
        //
        Navigator.get().navigateTo("signup-success");
    }

    @RequestMapping(value="signup", params = "confirm=email")
    public String confirm(@RequestParam Long userId, Model model) {
        //
        ProjectUser projectUser = projectUserService.getProjectUser(userId);
        model.addAttribute("projectUser", projectUser);
        //
        return "site/signup-confirm";
    }

}
