package org.focusns.web.console.env;

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

import org.focusns.common.web.widget.stereotype.Widget;
import org.focusns.model.env.Environment;
import org.focusns.service.env.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Widget
@RequestMapping("/console/env")
public class DbWidget {

    @Autowired
    private EnvironmentService environmentService;

    @RequestMapping("/db-view")
    public String db(Model model) {
        Environment envDB = environmentService.lookupEnvironment(Environment.Type.DB);
        model.addAttribute("envDB", envDB);
        return "console/env/db-view";
    }

}
