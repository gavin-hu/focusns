/*
package org.focusns.web.springmvc.initializer;

*/
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
 *//*


import org.focusns.common.plugin.PluginFilter;
import org.focusns.common.plugin.PluginListener;
import org.focusns.common.plugin.PluginMonitor;
import org.focusns.common.plugin.monitor.SimplePluginMonitor;
import org.focusns.web.helper.RuntimeHelper;
import org.focusns.web.springmvc.context.PluginWebApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import java.io.File;
import java.io.FileFilter;

public class PluginApplicationContextInitializer implements
        ApplicationContextInitializer<ConfigurableWebApplicationContext> {

    @Override
    public void initialize(ConfigurableWebApplicationContext webApplicationContext) {
        //
        if (webApplicationContext instanceof PluginWebApplicationContext) {
            File pluginDirs = RuntimeHelper.getPluginsDir();
            FileFilter pluginFilter = new PluginFilter();
            PluginListener pluginListener = (PluginListener) webApplicationContext;
            //
            PluginMonitor pluginMonitor = new SimplePluginMonitor(5000, 10000, pluginDirs, pluginFilter, pluginListener);
            //
            pluginMonitor.startup();
        }

    }
}
*/
