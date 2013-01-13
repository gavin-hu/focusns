/*
 * Copyright (C) 2011-2013 FocusSNS.
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
package org.focusns.web.modules.system.common;

import org.focusns.model.core.User;
import org.focusns.service.auth.AuthenticationException;
import org.focusns.service.auth.AuthenticationService;
import org.focusns.service.core.ProjectService;
import org.focusns.service.core.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private AuthenticationService authenticationService;
    
    @RequestMapping("/login.action")
    public String login(User user, HttpSession session) {
        //
        authenticationService.authenticate(user);
        //
        User dbUser = userService.getUser(user.getUsername());
        session.setAttribute("user", dbUser);
        //
        return "redirect:/" + dbUser.getProject().getCode() + "/profile" ;
    }
    
    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException() {
        //
        return "redirect:/login";
    }
}
