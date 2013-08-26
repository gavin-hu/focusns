package org.focusns.web.modules.team;

import org.focusns.common.web.widget.stereotype.Widget;
import org.focusns.web.widget.Constraint;
import org.focusns.web.widget.Constraints;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Widget
@RequestMapping("/team")
public class TeamMenuWidget {

    @RequestMapping(value="/menu-view", method = GET)
    @Constraints({ Constraint.PROJECT_NOT_NULL, Constraint.PROJECT_USER_NOT_NULL })
    public String doView() {
        return "modules/team/menu-view";
    }
}
