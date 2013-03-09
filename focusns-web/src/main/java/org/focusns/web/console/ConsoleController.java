package org.focusns.web.console;

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


import org.focusns.model.console.User;
import org.focusns.service.auth.AuthenticationException;
import org.focusns.web.helper.PropertyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Properties;

@Controller
@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    private Properties application;

    @RequestMapping("/login")
    public void login(User user, WebRequest webRequest) {
        String username = PropertyHelper.getConsoleUsername(application);
        String password = PropertyHelper.getConsolePassword(application);
        if(user.getUsername().equals(username)
                && user.getPassword().equals(password)) {
            //
            webRequest.setAttribute("user", user, WebRequest.SCOPE_SESSION);
        } else {
            throw new AuthenticationException("Username or Password miss matched!");
        }
    }

    @RequestMapping("/logout")
    public String logout(WebRequest webRequest) {
        //
        webRequest.removeAttribute("user", WebRequest.SCOPE_SESSION);
        //
        return "redirect:/console";
    }

}
