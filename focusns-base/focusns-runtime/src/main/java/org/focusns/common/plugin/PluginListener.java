package org.focusns.common.plugin;

import java.io.File;

public interface PluginListener {

    void changed(File[] pluginFiles);
}