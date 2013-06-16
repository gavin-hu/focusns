package org.focusns.common.web.widget.tag;

import org.apache.taglibs.standard.tag.el.core.UrlTag;
import org.focusns.common.web.page.config.WidgetConfig;

public class ActionUrlTag extends UrlTag {

    @Override
    public void setValue(String value_) {
        value_ = "/widget" + value_;
        //
        WidgetConfig widgetConfig = (WidgetConfig) pageContext.getRequest().getAttribute("widgetConfig");
        //
        if(value_.contains("?")) {
            value_ += "&widgetId=" + widgetConfig.getId();
        } else {
            value_ += "?widgetId=" + widgetConfig.getId();
        }
        //
        super.setValue(value_);
    }
}
