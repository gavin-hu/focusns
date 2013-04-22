package org.focusns.web.plugin;

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

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.focusns.web.helper.RuntimeHelper;

public class PluginManager {

    private PluginListener pluginListener;

    public PluginManager(PluginListener pluginListener) {
        this.pluginListener = pluginListener;
    }

    private Timer monitorTimer = new Timer("Plugin Manager");
    private TimerTask monitorTask = new TimerTask() {
        private Map<String, Long> lastModifiedCache = new HashMap<String, Long>();

        @Override
        public void run() {
            //
            try {
                List<URL> pluginUrlList = scanPlugins();
                if (!pluginUrlList.isEmpty()) {
                    // trigger listener
                    URL[] pluinUrls = pluginUrlList.toArray(new URL[pluginUrlList.size()]);
                    pluginListener.pluginChanged(pluinUrls);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private List<URL> scanPlugins() throws MalformedURLException {
            boolean modified = false;
            //
            List<URL> pluginUrlList = new ArrayList<URL>();
            File[] pluginFiles = RuntimeHelper.listPluginFiles(new PluginFileFilter());
            for (File pluginFile : pluginFiles) {
                String key = pluginFile.getAbsolutePath();
                long lastModified = pluginFile.lastModified();
                if (lastModifiedCache.containsKey(key)) {
                    long lastModifiedCached = lastModifiedCache.get(key);
                    if (lastModified > lastModifiedCached) {
                        //
                        modified = true;
                        lastModifiedCache.put(key, lastModified);
                    }
                } else {
                    //
                    modified = true;
                    lastModifiedCache.put(key, lastModified);
                }
                //
                if (modified) {
                    pluginUrlList.add(pluginFile.toURI().toURL());
                }
            }
            //
            return pluginUrlList;
        }
    };

    public void startup() {
        monitorTimer.schedule(monitorTask, 5000, 10000);
    }

    public void shutdown() {
        monitorTimer.cancel();
    }

    private class PluginFileFilter implements FileFilter {

        @Override
        public boolean accept(File file) {
            if (!file.canRead() || !file.getName().endsWith(".jar")) {
                return false;
            }
            //
            return true;
        }
    }

    public static void main(String[] args) {
        PluginManager pluginManager = new PluginManager(new PluginListener() {

            @Override
            public void pluginChanged(URL[] pluginUrls) {
                System.out.println("changed");
                //
                // PluginLoader pluginLoader = new PluginLoader(pluginUrls,
                // ClassLoader.getSystemClassLoader());
                //
                // ResourceLoader resourceLoader = new
                // DefaultResourceLoader(pluginLoader);

            }
        });
        pluginManager.startup();
    }

}
