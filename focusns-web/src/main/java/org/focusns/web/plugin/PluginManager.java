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
                List<URL> pluginUrlList = new ArrayList<URL>();
                if (scanPlugins(pluginUrlList)) {
                    // trigger listener
                    URL[] pluinUrls = pluginUrlList.toArray(new URL[pluginUrlList.size()]);
                    pluginListener.pluginChanged(pluinUrls);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private boolean scanPlugins(List<URL> pluginUrlList) throws MalformedURLException {
            boolean modified = false;
            //
            File[] pluginFiles = RuntimeHelper.listPluginFiles(new PluginFileFilter());
            //
            if(pluginFiles.length != lastModifiedCache.size()) {
                modified = true;
            }
            //
            for(File pluginFile : pluginFiles) {
                String key = pluginFile.getAbsolutePath();
                if(!lastModifiedCache.containsKey(key)) {
                    modified = true;
                }
            }
            //
            Map<String, Long> tmpLastModifiedCache = new HashMap<String, Long>();
            for (File pluginFile : pluginFiles) {
                String key = pluginFile.getAbsolutePath();
                long lastModified = pluginFile.lastModified();
                if (lastModifiedCache.containsKey(key)) {
                    long lastModifiedCached = lastModifiedCache.get(key);
                    if (lastModified > lastModifiedCached) {
                        //
                        modified = true;
                    }
                } else {
                    //
                    modified = true;
                }
                //
                tmpLastModifiedCache.put(key, lastModified);
            }
            //
            if(modified) {
                lastModifiedCache = tmpLastModifiedCache;
                //
                for(File pluginFile : pluginFiles) {
                    pluginUrlList.add(pluginFile.toURI().toURL());
                }
            }
            //
            return modified;
        }
    };

    public void startup(long delay, long period) {
        monitorTimer.schedule(monitorTask, delay, period);
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

}
