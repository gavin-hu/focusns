package org.focusns.web.site;

import org.focusns.common.web.widget.mvc.support.Navigator;
import org.focusns.common.web.widget.stereotype.Widget;
import org.focusns.web.Keys;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Widget
@RequestMapping("site")
public class LogoutWidget {

    @RequestMapping("logout")
    public void doLogout(WebRequest webRequest) {
        //
        webRequest.removeAttribute(Keys.SESSION_PROJECT_USER, WebRequest.SCOPE_SESSION);
        //
        Navigator.get().navigateTo("logout-success");
    }

}
