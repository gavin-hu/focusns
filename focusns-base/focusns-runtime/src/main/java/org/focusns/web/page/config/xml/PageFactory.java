/*
 * Copyright (C) 2012 FocuSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.focusns.web.page.config.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.focusns.web.page.config.PageConfig;
import org.focusns.web.page.config.WidgetConfig;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

public class PageFactory {
	
	private String prefix = "/WEB-INF/pages";
	
	private String suffix = ".xml";
	
	private ResourceLoader resourceLoader;
	
	public PageFactory(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
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
				WidgetConfig widgetDefinition = new WidgetConfig();
				widgetDefinition.setTarget(widgetEle.getAttribute("target"));
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
		return DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(pageResource.getInputStream()).getDocumentElement();
	}
	
	private String getLocation(String path) {
		return prefix + path + suffix;
	}
}
