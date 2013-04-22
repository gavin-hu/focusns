package org.focusns.common.plugin;

/*
 * #%L
 * FocusSNS Runtime
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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class PluginURLConnection extends URLConnection {

    private String resourceName;
    private Plugin plugin;

    public PluginURLConnection(URL pluginUrl, String resourceName, Plugin plugin) {
        super(pluginUrl);
        //
        this.resourceName = resourceName;
        this.plugin = plugin;
    }

    @Override
    public void connect() throws IOException {
        // do nothing!
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return plugin.getResourceAsStream(resourceName);
    }
}
