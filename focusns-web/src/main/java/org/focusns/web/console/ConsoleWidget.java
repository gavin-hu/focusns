package org.focusns.web.console;

import org.focusns.web.widget.annotation.Widget;

@Widget
public class ConsoleWidget {

    public String login() {
        return "console/login";
    }

    public String userMenu() {
        return "console/menu-user";
    }

    public String menu() {
        return "console/menu";
    }

}
