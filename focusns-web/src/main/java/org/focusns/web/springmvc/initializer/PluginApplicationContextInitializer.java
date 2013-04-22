package org.focusns.web.springmvc.initializer;

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

import org.focusns.web.plugin.PluginListener;
import org.focusns.web.plugin.PluginManager;
import org.focusns.web.springmvc.context.XmlPluginWebApplicationContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.web.context.ConfigurableWebApplicationContext;

public class PluginApplicationContextInitializer implements
        ApplicationContextInitializer<ConfigurableWebApplicationContext> {

    @Override
    public void initialize(ConfigurableWebApplicationContext webApplicationContext) {
        //
        if (webApplicationContext instanceof XmlPluginWebApplicationContext) {
            PluginListener pluginListener = (PluginListener) webApplicationContext;
            PluginManager pluginManager = new PluginManager(pluginListener);
            //
            pluginManager.startup(10000, 10000);
        }

    }
}
