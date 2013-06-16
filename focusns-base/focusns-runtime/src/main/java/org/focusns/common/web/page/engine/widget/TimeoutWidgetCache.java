package org.focusns.common.web.page.engine.widget;

import org.focusns.common.web.page.config.WidgetConfig;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
        if(timestamp==null || (timestamp.longValue() + widgetConfig.getCache() * 1000)<System.currentTimeMillis()) {
            return null;
        }
        Cache.ValueWrapper valueWrapper =  cache.get(widgetConfig.getId());
        return valueWrapper==null ? null : valueWrapper.get();
    }

}
