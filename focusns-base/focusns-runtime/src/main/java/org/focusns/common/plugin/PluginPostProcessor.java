package org.focusns.common.plugin;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.util.Enumeration;

public class PluginPostProcessor implements BeanDefinitionRegistryPostProcessor, ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassLoader classLoader = resourceLoader.getClassLoader();
        if(classLoader instanceof PluginClassLoader) {
            PluginClassLoader pluginLoader = (PluginClassLoader) classLoader;
            try {
                for(Enumeration<URL> e=pluginLoader.getResources(""); e.hasMoreElements();) {
                    String urlStr = e.nextElement().toExternalForm();
                    if(urlStr.startsWith("plugin:") && urlStr.endsWith(".class")) {
                        String classPathName = urlStr.substring("plugin:".length(), urlStr.indexOf(".class"));
                        String className = ClassUtils.convertResourcePathToClassName(classPathName);
                        Class<?> clazz = pluginLoader.loadClass(className);
                        if(clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(Service.class)
                                || clazz.isAnnotationPresent(Repository.class) || clazz.isAnnotationPresent(Component.class)) {
                            //
                            String beanName = StringUtils.capitalize(clazz.getSimpleName());
                            RootBeanDefinition beanDefinition = new RootBeanDefinition(clazz);
                            registry.registerBeanDefinition(beanName, beanDefinition);
                        }
                    }
                }
            } catch (Exception e) {
                throw new BeanCreationException(e.getMessage(), e);
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }


}
