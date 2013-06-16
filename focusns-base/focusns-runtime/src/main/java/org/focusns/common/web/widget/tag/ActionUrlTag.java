package org.focusns.common.web.widget.tag;

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

import org.apache.taglibs.standard.tag.el.core.UrlTag;
import org.focusns.common.web.page.config.WidgetConfig;

public class ActionUrlTag extends UrlTag {

    @Override
    public void setValue(String value_) {
        value_ = "/widget" + value_;
        //
        WidgetConfig widgetConfig = (WidgetConfig) pageContext.getRequest().getAttribute("widgetConfig");
        //
        if (value_.contains("?")) {
            value_ += "&widgetId=" + widgetConfig.getId();
        } else {
            value_ += "?widgetId=" + widgetConfig.getId();
        }
        //
        super.setValue(value_);
    }
}
