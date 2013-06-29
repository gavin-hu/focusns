package org.focusns.common.web.page;

import org.focusns.common.web.page.config.PageFactory;
import org.focusns.common.web.page.engine.PageEngine;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class PageFilterFactoryBean implements FactoryBean, InitializingBean {

    private PageEngine pageEngine;
    private PageFactory pageFactory;
    private String defaultLayout;
    //
    private PageFilter pageFilter;

    public void setPageEngine(PageEngine pageEngine) {
        this.pageEngine = pageEngine;
    }

    public void setPageFactory(PageFactory pageFactory) {
        this.pageFactory = pageFactory;
    }

    public void setDefaultLayout(String defaultLayout) {
        this.defaultLayout = defaultLayout;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(pageEngine, "Property pageEngine can not be null!");
        Assert.notNull(pageFactory, "Property pageFactory can not be null!");
        //
        this.pageFilter = new PageFilter();
        this.pageFilter.setPageEngine(pageEngine);
        this.pageFilter.setPageFactory(pageFactory);
        if(StringUtils.hasText(defaultLayout)) {
            this.pageFilter.setDefaultLayout(defaultLayout);
        }
    }

    @Override
    public Object getObject() throws Exception {
        return pageFilter;
    }

    @Override
    public Class<?> getObjectType() {
        return PageFilter.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
