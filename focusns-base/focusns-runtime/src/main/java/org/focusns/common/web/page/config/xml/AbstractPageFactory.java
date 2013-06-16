package org.focusns.common.web.page.config.xml;

/*
 * #%L
 * FocusSNS Runtime
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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.focusns.common.web.page.config.PageConfig;
import org.focusns.common.web.page.config.PageConfigException;
import org.focusns.common.web.page.config.PageConfigKey;
import org.focusns.common.web.page.config.PageFactory;
import org.focusns.common.web.page.config.PageKeyGenerator;

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
            for (PageConfig pageConfig : pageConfigList) {
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
