package org.focusns.common.web.widget.mvc.support;

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
import java.util.Map;

public class Navigator {

    public static final String KEY = "navigator";

    private static ThreadLocal<Navigator> navigatorLocal = new ThreadLocal<Navigator>() {
        @Override
        protected Navigator initialValue() {
            return new Navigator();
        }
    };

    private String navigateTo;
    private Map<String, Object> redirectAttributes = new HashMap<String, Object>();

    private Navigator() {
    }

    public static Navigator get() {
        return navigatorLocal.get();
    }

    public static void reset() {
        navigatorLocal.remove();
    }

    public Navigator withAttribute(String name, Object value) {
        redirectAttributes.put(name, value);
        return this;
    }

    public Map<String, Object> getRedirectAttributes() {
        return redirectAttributes;
    }

    public void navigateTo(String navigateTo) {
        this.navigateTo = navigateTo;
    }

    public String getNavigateTo() {
        return navigateTo;
    }
}
