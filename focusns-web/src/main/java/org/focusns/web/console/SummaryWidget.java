package org.focusns.web.console;

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


import org.focusns.model.env.Environment;
import org.focusns.service.env.EnvironmentService;
import org.focusns.web.widget.annotation.Widget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Widget
public class SummaryWidget {

    @Autowired
    private EnvironmentService environmentService;

    public String os(Map<String, Object> model) {
        Environment envOS = environmentService.lookupEnvironment(Environment.Type.OS);
        model.put("envOS", envOS);
        return "console/summary-os";
    }

    public String jre(Map<String, Object> model) {
        Environment envJava = environmentService.lookupEnvironment(Environment.Type.JRE);
        model.put("envJava", envJava);
        return "console/summary-jre";
    }

    public String db(Map<String, Object> model) {
        Environment envDB = environmentService.lookupEnvironment(Environment.Type.DB);
        model.put("envDB", envDB);
        return "console/summary-db";
    }

}
