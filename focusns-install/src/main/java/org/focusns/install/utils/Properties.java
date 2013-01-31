

package org.focusns.install.utils;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;

public class Properties extends java.util.Properties {

    private final LinkedHashSet<Object> keys = new LinkedHashSet<Object>();

    public Enumeration<Object> keys() {
        return Collections.<Object>enumeration(keys);
    }

    @Override
    public synchronized Object setProperty(String key, String value) {
        keys.add(key);
        return super.setProperty(key, value);
    }

    public Object put(Object key, Object value) {
        keys.add(key);
        return super.put(key, value);
    }

}
