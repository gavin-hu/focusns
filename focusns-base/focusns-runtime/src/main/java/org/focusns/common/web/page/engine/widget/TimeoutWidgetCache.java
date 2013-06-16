package org.focusns.common.web.page.engine.widget;

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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.focusns.common.web.page.config.WidgetConfig;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;

public class TimeoutWidgetCache implements WidgetCache {

    private Cache cache = new ConcurrentMapCache(WidgetCache.class.getSimpleName());
    //
    private Map<String, Long> timestampMap = new ConcurrentHashMap<String, Long>();

    public TimeoutWidgetCache() {
    }

    public TimeoutWidgetCache(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void put(WidgetConfig widgetConfig, Object value) {
        timestampMap.put(widgetConfig.getId(), System.currentTimeMillis());
        cache.put(widgetConfig.getId(), value);
    }

    @Override
    public Object get(WidgetConfig widgetConfig) {
        Long timestamp = timestampMap.get(widgetConfig.getId());
        if (timestamp == null || (timestamp.longValue() + widgetConfig.getCache() * 1000) < System.currentTimeMillis()) {
            return null;
        }
        Cache.ValueWrapper valueWrapper = cache.get(widgetConfig.getId());
        return valueWrapper == null ? null : valueWrapper.get();
    }

}
