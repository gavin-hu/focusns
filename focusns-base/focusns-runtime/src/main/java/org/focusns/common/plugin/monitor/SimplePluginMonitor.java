package org.focusns.common.plugin.monitor;

import org.focusns.common.plugin.PluginListener;
import org.focusns.common.plugin.PluginMonitor;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class SimplePluginMonitor implements PluginMonitor {

    private long delay;
    private long period;
    //
    private File pluginsDir;
    private FileFilter pluginFilter;
    private PluginListener pluginListener;
    //
    public SimplePluginMonitor(long delay, long period, File pluginsDir, FileFilter pluginFilter, PluginListener pluginListener) {
        this.delay = delay;
        this.period = period;
        this.pluginsDir = pluginsDir;
        this.pluginFilter = pluginFilter;
        this.pluginListener = pluginListener;
    }

    private Timer monitorTimer = new Timer("Plugin Manager");
    private TimerTask monitorTask = new TimerTask() {
        private Map<String, Long> lastModifiedCache = new HashMap<String, Long>();

        @Override
        public void run() {
            //
            try {
                List<File> pluginFiles = new ArrayList<File>();
                if (scanPlugins(pluginFiles)) {
                    // trigger listener
                    pluginListener.changed(pluginFiles.toArray(new File[pluginFiles.size()]));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private boolean scanPlugins(List<File> pluginUrlList) throws MalformedURLException {
            boolean modified = false;
            //
            File[] pluginFiles = pluginsDir.listFiles(pluginFilter);
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
                    pluginUrlList.add(pluginFile);
                }
            }
            //
            return modified;
        }
    };

    public void startup() {
        monitorTimer.schedule(monitorTask, delay, period);
        //
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                monitorTimer.cancel();
            }
        });
    }

    public void shutdown() {
        monitorTimer.cancel();
    }

}
