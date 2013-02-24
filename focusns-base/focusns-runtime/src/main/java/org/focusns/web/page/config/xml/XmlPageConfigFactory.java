package org.focusns.web.page.config.xml;

import java.util.ArrayList;
import java.util.List;

import org.focusns.common.xml.XmlParser;
import org.focusns.web.page.config.AbstractPageConfigFactory;
import org.focusns.web.page.config.PageConfig;
import org.focusns.web.page.config.PageConfigFactory;
import org.focusns.web.widget.config.WidgetConfig;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

public class XmlPageConfigFactory extends AbstractPageConfigFactory 
	implements PageConfigFactory, ResourceLoaderAware {
	
	private String prefix = "/WEB-INF/pages";
	private String suffix = ".xml";

	private ResourceLoader resourceLoader;
	
	private XmlParser xmlParser = new XmlParser();
	
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public PageConfig loadPage(String path) throws Exception {
		//
		String pageLocation = getLocation(path);
		Resource pageResource = resourceLoader.getResource(pageLocation);
		return parse(getPageElement(pageResource));
	}
	
	private PageConfig parse(Element element) throws Exception {
		PageConfig pageDefinition;
		if(element.hasAttribute("extends")) {
			String extendsPath = element.getAttribute("extends");
			pageDefinition = loadPage(extendsPath);
		} else {
			pageDefinition = new PageConfig();
		}
		//
		List<Element> positionEles = DomUtils.getChildElementsByTagName(element, "position");
		for(Element positionEle : positionEles) {
			String position = positionEle.getAttribute("name");
			List<WidgetConfig> widgetDefinitions = new ArrayList<WidgetConfig>();
			//
			List<Element> widgetEles = DomUtils.getChildElementsByTagName(positionEle, "widget");
			for(Element widgetEle : widgetEles) {
				WidgetConfig widgetDefinition = new WidgetConfig(widgetEle.getAttribute("target"));
                Element prefsEle = DomUtils.getChildElementByTagName(widgetEle, "preference");
                if(prefsEle!=null) {
                    for(Element prefEle : DomUtils.getChildElements(prefsEle)) {
                        String name = prefEle.getTagName();
                        String value = DomUtils.getTextValue(prefEle);
                        widgetDefinition.getPreferences().put(name, value);
                    }
                }
				//
				widgetDefinitions.add(widgetDefinition);
			}
			//
			pageDefinition.addWidgetConfigList(position, widgetDefinitions);
		}
		//
		return pageDefinition;
	}
	
	private Element getPageElement(Resource pageResource) throws Exception {
		return xmlParser.parse(pageResource).getDocumentElement();
	}
	
	private String getLocation(String path) {
		if(path.contains(".")) {
			path = path.substring(0, path.indexOf("."));
		}
		//
		if(path.endsWith("/")) {
			path = path.substring(0, path.length()-1);
		}
		return prefix + path + suffix;
	}
}
