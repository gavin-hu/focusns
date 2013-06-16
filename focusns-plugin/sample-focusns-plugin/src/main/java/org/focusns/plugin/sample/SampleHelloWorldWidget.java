package org.focusns.plugin.sample;

import org.focusns.common.web.widget.stereotype.Widget;
import org.springframework.web.bind.annotation.RequestMapping;

@Widget
public class SampleHelloWorldWidget {

    @RequestMapping("hello-world")
    public String helloWord() {
        return "sample/hello-world";
    }

}
