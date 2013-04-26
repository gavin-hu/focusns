package org.focusns.common.plugin;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginFilter implements FileFilter {

    @Override
    public boolean accept(File file) {
        if (!file.canRead() || !file.getName().endsWith(".jar")) {
            return false;
        }
        //
        return true;
    }

}
