package org.focusns.web.springmvc.context;

import org.focusns.common.plugin.PluginClassLoader;
import org.focusns.common.plugin.PluginListener;
import org.focusns.web.portal.config.PageConfig;
import org.focusns.web.portal.config.PageConfigKey;
import org.focusns.web.portal.config.xml.XmlPageConfigParser;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PluginWebApplicationContext extends XmlWebApplicationContext implements PluginListener {

    @Override
    public void changed(File[] pluginFiles) {
        //
        try {
            //
            unloadPluginPages();
            //
            preparePluginClassLoader(pluginFiles);
            //
            refresh();
            //
            loadPluginPages();
            //
        } catch (Exception e) {
            getServletContext().log(e.getMessage(), e);
        }
    }

    protected void preparePluginClassLoader(File[] pluginFiles) throws IOException {
        //
        File pluginsDir = getResourceByPath("/WEB-INF/plugins").getFile();
        //
        ClassLoader parent = Thread.currentThread().getContextClassLoader();
        ClassLoader pluginClassLoader = new PluginClassLoader(pluginsDir, pluginFiles, parent);
        //
        setClassLoader(pluginClassLoader);
    }

    protected void unloadPluginPages() {
        //
        getServletContext().removeAttribute("pluginPageConfigMap");
    }

    protected void loadPluginPages() throws Exception {
        //
        Map<String, PageConfig> pluginPageConfigMap = new HashMap<String, PageConfig>();
        Resource[] resources = getResources("classpath*:/META-INF/focusns-plugin.xml");
        for(Resource resource : resources) {
            List<PageConfig> pageConfigList = XmlPageConfigParser.parse(resource.getInputStream());
            for(PageConfig pageConfig : pageConfigList) {
                String key = PageConfigKey.generateKey(pageConfig);
                pluginPageConfigMap.put(key, pageConfig);
            }
        }
        //
        getServletContext().setAttribute("pluginPageConfigMap", pluginPageConfigMap);
    }

}
