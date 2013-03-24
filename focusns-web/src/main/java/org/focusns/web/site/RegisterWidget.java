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


import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectUserService;
import org.focusns.validation.group.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/site")
public class RegisterWidget {

    @Autowired
    private ProjectUserService projectUserService;

    @RequestMapping("/register-form")
    public String doEdit() {
        return "site/register-form";
    }

    @RequestMapping("/register-user")
    public String doCreate(@Validated(Register.class) ProjectUser user) {
        //
        projectUserService.createUser(user);
        //
        return "redirect:/welcome";
    }

}
