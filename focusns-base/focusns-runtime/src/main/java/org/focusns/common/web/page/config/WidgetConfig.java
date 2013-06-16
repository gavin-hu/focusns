package org.focusns.common.web.page.config;

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

import org.springframework.core.Ordered;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

public class WidgetConfig implements Ordered {

    private PositionConfig positionConfig;
    //
    private String styleId;
    private String styleClass;
    //
    private String id;
    private String mode;
    private String target;
    private String context;
    private int cache;
    //
    private int order;
    // navigation
    private Map<String, String> navigationMap = new HashMap<String, String>();
    // validation
    private String validationForm;
    private String validationTarget;
    private List<String> validationGroups = new ArrayList<String>();
    //
    private Map<String, Object> preferences = new HashMap<String, Object>();

    public WidgetConfig(PositionConfig positionConfig) {
        this.positionConfig = positionConfig;
    }

    public String getStyleId() {
        if (!StringUtils.hasText(styleId)) {
            this.styleId = generateStyleId();
        }
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getId() {
        if (id == null) {
            this.id = generateId();
        }
        return id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getCache() {
        return cache;
    }

    public void setCache(int cache) {
        this.cache = cache;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Map<String, String> getNavigationMap() {
        return navigationMap;
    }

    public void setNavigationMap(Map<String, String> navigationMap) {
        this.navigationMap = navigationMap;
    }

    public String getValidationForm() {
        return validationForm;
    }

    public void setValidationForm(String validationForm) {
        this.validationForm = validationForm;
    }

    public String getValidationTarget() {
        return validationTarget;
    }

    public void setValidationTarget(String validationTarget) {
        this.validationTarget = validationTarget;
    }

    public List<String> getValidationGroups() {
        return validationGroups;
    }

    public void setValidationGroups(List<String> validationGroups) {
        this.validationGroups = validationGroups;
    }

    public Map<String, Object> getPreferences() {
        return preferences;
    }

    public void setPreferences(Map<String, Object> preferences) {
        this.preferences = preferences;
    }

    private String generateId() {
        StringBuffer sb = new StringBuffer(context + target + mode);
        sb.append(positionConfig.getName());
        sb.append(positionConfig.getPageConfig().getPath());
        //
        return DigestUtils.md5DigestAsHex(sb.toString().getBytes());
    }

    private String generateStyleId() {
        return DigestUtils.md5DigestAsHex((context + target + mode).getBytes());
    }
}
