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
package org.focusns.web.initializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.focusns.web.helper.ApplicationHelper;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class ApplicationInitializer  implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final Log log = LogFactory.getLog(ApplicationInitializer.class);

	public void initialize(ConfigurableApplicationContext applicationContext) {
        // check runtime environment
        checkRuntimeInstalled();
	}

    private void checkRuntimeInstalled() {
        if(!ApplicationHelper.getInstance().isInstalled()) {
            throw new RuntimeException("FocusSNS Runtime is not installed now!");
        }
    }
}
