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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import org.springframework.util.FileCopyUtils;

public class PluginLoader extends ClassLoader {

    private List<Plugin> pluginList = new ArrayList<Plugin>();

    public PluginLoader(URL[] pluginUrls, ClassLoader parent) throws IOException {
        super(parent);
        //
        initialize(pluginUrls);
    }

    private void initialize(URL[] pluginUrls) throws IOException {
        //
        for (URL pluginUrl : pluginUrls) {
            pluginList.add(new Plugin(pluginUrl));
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //
        for(Plugin plugin : pluginList) {
            byte[] classBytes = plugin.findClassBytes(name);
            if(classBytes!=null) {
                return defineClass(name, classBytes, 0, classBytes.length);
            }
        }
        //
        return super.findClass(name);
    }

    @Override
    public URL getResource(String name) {
        URL url = super.getResource(name);
        if (url == null) {
            for (Plugin plugin : pluginList) {
                url = plugin.getResource(name);
                if (url != null) {
                    return url;
                }
            }
        }
        //
        return url;
    }

    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        //
        Vector<URL> vector = new Vector<URL>();
        for (Enumeration<URL> e = super.getResources(name); e.hasMoreElements();) {
            vector.add(e.nextElement());
        }
        for (Plugin plugin : pluginList) {
            URL url = plugin.getResource(name);
            if(url!=null) {
                vector.add(url);
            }
        }
        //
        return vector.elements();
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        //
        InputStream in = super.getResourceAsStream(name);
        if (in == null) {
            for (Plugin plugin : pluginList) {
                in = plugin.getResourceAsStream(name);
                if (in != null) {
                    return in;
                }
            }
        }
        //
        return in;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("C:\\Users\\Gavin\\.focusns\\plugins\\demo-focusns-plugin-2.0.0-SNAPSHOT.jar");
        URL jarFileUrl = file.toURI().toURL();
        //
        PluginLoader pluginLoader = new PluginLoader(new URL[] { jarFileUrl }, getSystemClassLoader());
        Class clazz = pluginLoader.loadClass("org.focusns.plugin.demo.DemoWidget");
        System.out.println(clazz);
    }

}
