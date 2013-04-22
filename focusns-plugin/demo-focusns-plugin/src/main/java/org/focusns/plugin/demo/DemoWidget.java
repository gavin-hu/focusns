package org.focusns.plugin.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/plugin")
public class DemoWidget {

    @RequestMapping("/demo")
    public String doView() {
        return "plugin:demo/view";
    }

}
