package org.focusns.web.site;

import org.focusns.common.web.widget.mvc.support.Navigator;
import org.focusns.common.web.widget.stereotype.Widget;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.auth.AuthenticationException;
import org.focusns.service.auth.AuthenticationService;
import org.focusns.service.core.ProjectService;
import org.focusns.service.core.ProjectUserService;
import org.focusns.web.Keys;
import org.focusns.web.widget.Constraint;
import org.focusns.web.widget.Constraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Widget
@RequestMapping("site")
public class LoginWidget {

    @Autowired
    private ProjectUserService projectUserService;
    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping("login-form")
    @Constraints({ Constraint.PROJECT_USER_IS_NULL})
    public void doForm() {
    }

    @RequestMapping("login-bar")
    @Constraints({ Constraint.PROJECT_USER_IS_NULL})
    public void doBar() {
    }

    @RequestMapping("/login")
    public void doLogin(@ModelAttribute ProjectUser user, WebRequest webRequest) {
        //
        authenticationService.authenticate(user);
        //
        ProjectUser dbUser = projectUserService.getUser(user.getUsername());
        webRequest.setAttribute(Keys.SESSION_PROJECT_USER, dbUser, WebRequest.SCOPE_SESSION);
        //
        Navigator.get().navigateTo("login-success");
    }

    @ExceptionHandler(AuthenticationException.class)
    public void handleAuthenticationException(AuthenticationException e) {
        Navigator.get().navigateTo("auth-failure");
    }

}
