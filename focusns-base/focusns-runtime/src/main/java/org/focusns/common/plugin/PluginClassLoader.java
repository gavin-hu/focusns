package org.focusns.common.plugin;

import org.focusns.common.io.FileUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarFile;

public class PluginClassLoader extends ClassLoader {

    private List<ClassLoader> classLoaders = new ArrayList<ClassLoader>();

    public PluginClassLoader(File pluginsDir, File[] pluginFiles, ClassLoader parent) throws IOException {
        super(parent);
        //
        initialize(pluginsDir, pluginFiles);
    }

    private void initialize(File pluginsDir, File[] pluginFiles) throws IOException {
        //
        for(File pluginFile : pluginFiles) {
            //
            String pluginName = pluginFile.getName().substring(0, pluginFile.getName().indexOf(".jar"));
            File pluginDir = new File(pluginsDir, pluginName);
            //
            JarFile jarFile = new JarFile(pluginFile);
            FileUtils.copyJarFile(jarFile, pluginDir);
            //

            URL tmpPluginUrl = pluginDir.toURI().toURL();
            this.classLoaders.add(new URLClassLoader(new URL[]{tmpPluginUrl}));
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //
        String classPath = ClassUtils.convertClassNameToResourcePath(name)+".class";
        URL url = getResource(classPath);
        if(url!=null) {
            try {
                byte[] classBytes = FileCopyUtils.copyToByteArray(url.openStream());
                return defineClass(name, classBytes, 0, classBytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //
        return super.findClass(name);
    }

    @Override
    protected URL findResource(String name) {
        //
        for(ClassLoader classLoader : classLoaders) {
            URL url = classLoader.getResource(name);
            if(url != null) {
                return url;
            }
        }
        //
        return super.findResource(name);
    }

    @Override
    public Enumeration<URL> findResources(String name) throws IOException {
        //
        for(ClassLoader classLoader : classLoaders) {
            Enumeration<URL> e = classLoader.getResources(name);
            if(e != null) {
                return e;
            }
        }
        //
        return super.findResources(name);
    }

}
