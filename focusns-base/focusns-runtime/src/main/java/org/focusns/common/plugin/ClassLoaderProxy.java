package org.focusns.common.plugin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class ClassLoaderProxy extends ClassLoader {


    private PluginClassLoader pluginClassLoader;

    public ClassLoaderProxy(File pluginsDir, File[] pluginFiles, ClassLoader parent) throws IOException {
        super(parent);
        //
        this.pluginClassLoader = new PluginClassLoader(pluginsDir, pluginFiles, parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //
        Class<?> c = pluginClassLoader.loadClass(name);
        if(c!=null) {
            return c;
        }
        //
        throw new ClassNotFoundException(name);
    }

    @Override
    protected URL findResource(String name) {
        //
        URL url = pluginClassLoader.getResource(name);
        if(url != null) {
            return url;
        }
        //
        return super.findResource(name);
    }

    @Override
    public Enumeration<URL> findResources(String name) throws IOException {
        //
        Enumeration<URL> e = pluginClassLoader.getResources(name);
        if(e != null) {
            return e;
        }
        //
        return super.getResources(name);
    }

}
