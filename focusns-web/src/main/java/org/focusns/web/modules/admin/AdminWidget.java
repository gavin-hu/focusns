package org.focusns.web.modules.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/admin")
public class AdminWidget {

    @RequestMapping(value="/menu-view", method = GET)
    public String doView() {
        return "modules/admin/menu-view";
    }


}
