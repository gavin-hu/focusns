package org.focusns.common.web.page.config.xml;

import org.focusns.common.web.page.config.PageConfig;
import org.focusns.common.web.page.config.PageConfigException;
import org.focusns.common.web.page.config.PageConfigKey;
import org.focusns.common.web.page.config.PageFactory;
import org.focusns.common.web.page.config.PageKeyGenerator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractPageFactory implements PageFactory {

    private boolean cacheable;
    private PageKeyGenerator pageKeyGenerator;
    //
    private Map<String, PageConfig> cache = new HashMap<String, PageConfig>();

    public void setCacheable(boolean cacheable) {
        this.cacheable = cacheable;
    }

    public void setPageKeyGenerator(PageKeyGenerator pageKeyGenerator) {
        this.pageKeyGenerator = pageKeyGenerator;
    }

    @Override
    public PageConfig find(String name, Map<String, String> params) throws PageConfigException {
        try {
            String pageKey = pageKeyGenerator.generate(name, params);
            if (cacheable && cache.containsKey(pageKey)) {
                return cache.get(pageKey);
            }
            //
            List<PageConfig> pageConfigList = loadPages();
            for(PageConfig pageConfig : pageConfigList) {
                String tmpPageKey = pageKeyGenerator.generate(pageConfig.getPath(), pageConfig.getParameters());
                this.cache.put(tmpPageKey, pageConfig);
            }
            //
            return cache.get(pageKey);
        } catch (Exception e) {
            throw new PageConfigException(e.getMessage(), e);
        }
    }

    protected abstract List<PageConfig> loadPages() throws Exception;

}
