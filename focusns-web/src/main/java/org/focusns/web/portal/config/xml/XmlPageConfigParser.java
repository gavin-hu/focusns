package org.focusns.web.portal.config.xml;

import org.focusns.common.xml.XmlParser;
import org.focusns.web.portal.config.PageConfig;
import org.focusns.web.portal.config.PositionConfig;
import org.focusns.web.portal.config.WidgetConfig;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlPageConfigParser {

    private static XmlParser xmlParser = new XmlParser();

    public static List<PageConfig> parse(InputStream inputStream) throws Exception {
        Element docElement = xmlParser.parse(inputStream).getDocumentElement();
        return parse(docElement);
    }

    private static List<PageConfig> parse(Element element) throws Exception {
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
                int index = 1;
                List<Element> widgetEles = DomUtils.getChildElementsByTagName(positionEle, "widget");
                for (Element widgetEle : widgetEles) {
                    String target = widgetEle.getAttribute("target");
                    String id = DigestUtils.md5DigestAsHex(target.getBytes());
                    String order = widgetEle.getAttribute("order");
                    //
                    if(!StringUtils.hasText(order)) {
                        order = String.valueOf(100 * index++);
                    }
                    //
                    WidgetConfig widgetConfig = new WidgetConfig(id, order, target);
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

}
