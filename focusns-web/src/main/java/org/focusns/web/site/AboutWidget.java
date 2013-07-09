package org.focusns.web.site;

import org.focusns.common.web.widget.stereotype.Widget;
import org.springframework.web.bind.annotation.RequestMapping;

@Widget
@RequestMapping("site")
public class AboutWidget {

    @RequestMapping("about-view")
    public String doView() {
        return "site/about-view";
    }

}
