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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.focusns.common.web.page.config.PageConfig;
import org.focusns.common.web.page.config.PageConfigKey;
import org.focusns.common.web.page.config.PositionConfig;
import org.focusns.common.web.page.config.WidgetConfig;
import org.focusns.common.xml.XmlParser;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class XmlPageFactory extends AbstractPageFactory implements ResourceLoaderAware {

    private String prefix = "/WEB-INF/pages";
    private String suffix = ".xml";

    private ResourceLoader resourceLoader;

    private XmlParser xmlParser = new XmlParser();

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
    protected List<PageConfig> loadPages() throws Exception {
        //
        Map<String, PageConfig> abstractPageConfigMap = new HashMap<String, PageConfig>();
        List<PageConfig> pageConfigList = new ArrayList<PageConfig>();
        //
        ResourcePatternResolver rpr = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        Resource[] resources = rpr.getResources(prefix + "/**/*" + suffix);
        // parse abstract pages
        for (Resource resource : resources) {
            Document xmlDoc = xmlParser.parse(resource.getInputStream());
            Element pagesEle = xmlDoc.getDocumentElement();
            List<Element> pageEles = DomUtils.getChildElementsByTagName(pagesEle, "page");
            for (Element pageEle : pageEles) {
                String abstractAttr = pageEle.getAttribute("abstract");
                if (StringUtils.hasText(abstractAttr)) {
                    PageConfig pageConfig = parsePageConfigFile(pageEle);
                    abstractPageConfigMap.put(abstractAttr, pageConfig);
                }
            }
        }
        // parse pages
        for (Resource resource : resources) {
            Document xmlDoc = xmlParser.parse(resource.getInputStream());
            Element pagesEle = xmlDoc.getDocumentElement();
            List<Element> pageEles = DomUtils.getChildElementsByTagName(pagesEle, "page");
            for (Element pageEle : pageEles) {
                String abstractAttr = pageEle.getAttribute("abstract");
                if (!StringUtils.hasText(abstractAttr)) {
                    PageConfig pageConfig = parsePageConfigFile(pageEle);
                    pageConfigList.add(pageConfig);
                }
            }
        }
        // merges pages
        List<PageConfig> mergedPageConfigList = new ArrayList<PageConfig>();
        for (PageConfig pageConfig : pageConfigList) {
            String extendsTarget = pageConfig.getParameters().get("extends");
            if (StringUtils.hasText(extendsTarget)) {
                PageConfig abstractPageConfig = abstractPageConfigMap.get(extendsTarget);
                //
                PageConfig newPageConfig = new PageConfig(pageConfig.getPath(), pageConfig.getLayout());
                newPageConfig.setParameters(pageConfig.getParameters());
                // add widget from abstract page
                for (String position : abstractPageConfig.getPositionConfigMap().keySet()) {
                    PositionConfig abstractPositionConfig = abstractPageConfig.getPositionConfigMap().get(position);
                    newPageConfig.addPositionConfig(abstractPositionConfig);
                }
                // add widget from extends page
                for (String position : pageConfig.getPositionConfigMap().keySet()) {
                    PositionConfig positionConfig = pageConfig.getPositionConfigMap().get(position);
                    newPageConfig.addPositionConfig(positionConfig);
                }
                //
                mergedPageConfigList.add(newPageConfig);
            } else {
                mergedPageConfigList.add(pageConfig);
            }
        }
        //
        return mergedPageConfigList;
    }

    private PageConfig parsePageConfigFile(Element pageEle) {
        //
        String path = pageEle.getAttribute("path");
        String layout = pageEle.getAttribute("layout");
        Map<String, String> parameters = getParameters(pageEle);
        //
        PageConfig pageConfig = new PageConfig(path, layout);
        pageConfig.setParameters(parameters);
        List<Element> positionEles = DomUtils.getChildElementsByTagName(pageEle, "position");
        for (Element positionEle : positionEles) {
            //
            String position = positionEle.getAttribute("name");
            PositionConfig positionConfig = new PositionConfig(pageConfig);
            positionConfig.setName(position);
            List<Element> widgetEles = DomUtils.getChildElementsByTagName(positionEle, "widget");
            for (Element widgetEle : widgetEles) {
                //
                String styleId = widgetEle.getAttribute("styleId");
                String styleClass = widgetEle.getAttribute("styleClass");
                //
                String context = widgetEle.getAttribute("context");
                String target = widgetEle.getAttribute("target");
                String mode = widgetEle.getAttribute("mode");
                String cache = widgetEle.getAttribute("cache");
                String order = widgetEle.getAttribute("order");
                //
                WidgetConfig widgetConfig = new WidgetConfig(positionConfig);
                widgetConfig.setStyleId(styleId);
                widgetConfig.setStyleClass(styleClass);
                widgetConfig.setContext(context);
                widgetConfig.setTarget(target);
                widgetConfig.setMode(mode);
                widgetConfig.setCache("".equals(cache) ? 0 : Integer.parseInt(cache));
                widgetConfig.setOrder("".equals(order) ? 0 : Integer.parseInt(order));
                // preference element
                Element preferenceEle = DomUtils.getChildElementByTagName(widgetEle, "preference");
                if (preferenceEle != null) {
                    for (Element prefEle : DomUtils.getChildElements(preferenceEle)) {
                        String name = prefEle.getTagName();
                        String value = DomUtils.getTextValue(prefEle);
                        widgetConfig.getPreferences().put(name, value);
                    }
                }
                // navigation
                Element navigationEle = DomUtils.getChildElementByTagName(widgetEle, "navigation");
                if (navigationEle != null) {
                    Map<String, String> navigationMap = new HashMap<String, String>();
                    List<Element> eventEles = DomUtils.getChildElementsByTagName(navigationEle, "event");
                    for (Element eventEle : eventEles) {
                        String eventOn = eventEle.getAttribute("on");
                        String eventTo = DomUtils.getTextValue(eventEle);
                        navigationMap.put(eventOn, eventTo);
                    }
                    widgetConfig.setNavigationMap(navigationMap);
                }
                // validation
                Element validationEle = DomUtils.getChildElementByTagName(widgetEle, "validation");
                if (validationEle != null) {
                    widgetConfig.setValidationForm(validationEle.getAttribute("formId"));
                    Element targetEle = DomUtils.getChildElementByTagName(validationEle, "target");
                    if (targetEle != null) {
                        widgetConfig.setValidationTarget(DomUtils.getTextValue(targetEle).trim());
                    }
                    Element groupsEle = DomUtils.getChildElementByTagName(validationEle, "groups");
                    if (groupsEle != null) {
                        List<String> validationGroups = new ArrayList<String>();
                        List<Element> groupEles = DomUtils.getChildElementsByTagName(groupsEle, "group");
                        for (Element groupEle : groupEles) {
                            validationGroups.add(DomUtils.getTextValue(groupEle).trim());
                        }
                        widgetConfig.setValidationGroups(validationGroups);
                    }
                }
                //
                positionConfig.addWidgetConfig(widgetConfig);
                // pageConfig.addWidgetConfig(position, widgetConfig);
            }
            pageConfig.addPositionConfig(positionConfig);
        }
        //
        return pageConfig;
    }

    private Map<String, String> getParameters(Element pageEle) {
        Map<String, String> parameters = new HashMap<String, String>();
        NamedNodeMap nnm = pageEle.getAttributes();
        for (int i = 0; i < nnm.getLength(); i++) {
            Node node = nnm.item(i);
            String paramName = node.getNodeName();
            String paramValue = node.getNodeValue();
            parameters.put(paramName, paramValue);
        }
        return parameters;
    }

}
