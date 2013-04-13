package org.focusns.web.portal.config.xml;

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

import org.focusns.common.xml.XmlParser;
import org.focusns.web.portal.config.*;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class XmlPageConfigFactory extends AbstractPageConfigFactory implements PageConfigFactory, ResourceLoaderAware {

    private String prefix = "/WEB-INF/portal/";
    private String suffix = ".xml";

    private ResourceLoader resourceLoader;

    private XmlParser xmlParser = new XmlParser();

    private AtomicLong atomicLong = new AtomicLong(0);

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    protected Map<String, PageConfig> loadPages() throws Exception {
        //
        Map<String, PageConfig> pageConfigMap = new HashMap<String, PageConfig>();
        //
        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(prefix + "/**/*" + suffix);
        for (Resource resource : resources) {
            List<PageConfig> pageConfigList = parse(getRootElement(resource));
            for (PageConfig pageConfig : pageConfigList) {
                Map<String, String> paramsMap = new HashMap<String, String>();
                paramsMap.put("mode", pageConfig.getMode());
                paramsMap.put("category", pageConfig.getCategory());
                String key = generateKey(pageConfig.getPath(), paramsMap);
                pageConfigMap.put(key, pageConfig);
            }
        }
        //
        return pageConfigMap;
    }

    private List<PageConfig> parse(Element element) throws Exception {
        //
        List<PageConfig> pageConfigList = new ArrayList<PageConfig>();
        //
        List<Element> pageEles = Arrays.asList(element);
        if ("pages".equals(element.getTagName())) {
            pageEles = DomUtils.getChildElementsByTagName(element, "page");
        }
        //
        for (Element pageEle : pageEles) {
            //
            PageConfig pageConfig = new PageConfig();
            pageConfig.setPath(pageEle.getAttribute("path"));
            pageConfig.setMode(pageEle.getAttribute("mode"));
            pageConfig.setCategory(pageEle.getAttribute("category"));
            //
            List<Element> positionEles = DomUtils.getChildElementsByTagName(pageEle, "position");
            for (Element positionEle : positionEles) {
                PositionConfig positionConfig = new PositionConfig();
                positionConfig.setName(positionEle.getAttribute("name"));
                positionConfig.setGrid(positionEle.getAttribute("grid"));
                //
                List<Element> widgetEles = DomUtils.getChildElementsByTagName(positionEle, "widget");
                for (Element widgetEle : widgetEles) {
                    WidgetConfig widgetConfig = new WidgetConfig(widgetEle.getAttribute("target"));
                    widgetConfig.setId(String.valueOf(atomicLong.incrementAndGet()));
                    // preferences element
                    Element prefsEle = DomUtils.getChildElementByTagName(widgetEle, "preference");
                    if (prefsEle != null) {
                        for (Element prefEle : DomUtils.getChildElements(prefsEle)) {
                            String name = prefEle.getTagName();
                            String value = DomUtils.getTextValue(prefEle);
                            widgetConfig.getPreferences().put(name, value);
                        }
                    }
                    //
                    positionConfig.addWidgetConfig(widgetConfig);
                }
                //
                pageConfig.addPositionConfig(positionConfig);
                //
                pageConfigList.add(pageConfig);
            }
        }
        //
        return pageConfigList;
    }

    private Element getRootElement(Resource pageResource) throws Exception {
        return xmlParser.parse(pageResource).getDocumentElement();
    }

}
