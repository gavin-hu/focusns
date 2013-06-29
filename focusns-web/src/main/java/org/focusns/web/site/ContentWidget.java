package org.focusns.web.site;

import org.focusns.common.web.widget.annotation.bind.WidgetPref;
import org.focusns.common.web.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Widget
@RequestMapping("site")
public class ContentWidget {

    @RequestMapping("content-view")
    public String doView(@WidgetPref String title, @WidgetPref String content, Model model) {
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        return "site/content-view";
    }

}
