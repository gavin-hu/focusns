package org.focusns.web.site;

/*
 * #%L
 * FocusSNS Web
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


import org.focusns.model.core.ProjectUser;
import org.focusns.service.auth.AuthenticationService;
import org.focusns.service.core.ProjectService;
import org.focusns.service.core.ProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/site")
public class LogInAndOutWidget {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectUserService projectUserService;
    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping("/login-form")
    public String doEdit() {
        return "site/login-form";
    }

    @RequestMapping("/login")
    public String doLogin(@RequestParam(required = false) String redirect,
                          @ModelAttribute ProjectUser user, WebRequest webRequest) {
        //
        authenticationService.authenticate(user);
        //
        ProjectUser dbUser = projectUserService.getUser(user.getUsername());
        webRequest.setAttribute("user", dbUser, WebRequest.SCOPE_SESSION);
        webRequest.setAttribute(ProjectUser.KEY, dbUser, WebRequest.SCOPE_SESSION);
        //
        if(StringUtils.hasText(redirect)) {
            return "redirect:"+redirect;
        }
        //
        return "redirect:/" + dbUser.getProject().getCode() + "/profile" ;
    }

    @RequestMapping("/logout")
    public String doLogout(WebRequest webRequest) {
        //
        webRequest.removeAttribute("user", WebRequest.SCOPE_SESSION);
        webRequest.removeAttribute(ProjectUser.KEY, WebRequest.SCOPE_SESSION);
        //
        return "redirect:/login";
    }

}
