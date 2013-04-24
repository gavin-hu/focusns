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

import com.googlecode.flyway.core.util.FileCopyUtils;
import org.springframework.util.ClassUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Plugin {

    private String name;
    private URL url;
    //
    private Map<String, byte[]> pluginResources = new HashMap<String, byte[]>();

    public Plugin(URL url) throws IOException {
        initialize(url);
    }

    private void initialize(URL url) throws IOException {
        this.url = url;
        //
        File file = new File(url.getFile());
        this.name = file.getName();
        //
        JarFile jarFile = new JarFile(file);
        for (Enumeration<JarEntry> e = jarFile.entries(); e.hasMoreElements();) {
            JarEntry jarEntry = e.nextElement();
            String key = jarEntry.getName();
            byte[] value = FileCopyUtils.copyToByteArray(jarFile.getInputStream(jarEntry));
            //
            pluginResources.put(key, value);
        }
        jarFile.close();
    }

    public String getName() {
        return name;
    }

    public byte[] findClassBytes(String className) {
        String resourceName = ClassUtils.convertClassNameToResourcePath(className) + ".class";
        return pluginResources.get(resourceName);
    }

    public URL getResource(final String name) {
        URL resourceUrl = null;
        //
        if (pluginResources.containsKey(name)) {
            try {
                resourceUrl = new URL("plugin", null, 0, name, new URLStreamHandler() {
                    @Override
                    protected URLConnection openConnection(URL u) throws IOException {
                        return new PluginURLConnection(u, name, Plugin.this);
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        //
        return resourceUrl;
    }

    public List<URL> getResources(String name) {
        //
        List<URL> urlList = new ArrayList<URL>();
        for(String resourceName : pluginResources.keySet()) {
            if(resourceName.startsWith(name)) {
                urlList.add(getResource(resourceName));
            }
        }
        //
        return urlList;
    }

    public InputStream getResourceAsStream(String name) {
        byte[] resource = pluginResources.get(name);
        return new ByteArrayInputStream(resource);
    }

}
