package org.focusns.web.site;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.auth.AuthenticationException;
import org.focusns.service.auth.AuthenticationService;
import org.focusns.service.core.ProjectService;
import org.focusns.service.core.ProjectUserService;
import org.focusns.validation.group.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/site")
public class SiteController {

    private static final Log log = LogFactory.getLog(SiteController.class);

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectUserService projectUserService;
    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping("/login")
    public String login(@Validated ProjectUser user, WebRequest webRequest) {
        //
        authenticationService.authenticate(user);
        //
        ProjectUser dbUser = projectUserService.getUser(user.getUsername());
        webRequest.setAttribute("user", dbUser, WebRequest.SCOPE_SESSION);
        //
        return "redirect:/" + dbUser.getProject().getCode() + "/profile" ;
    }

    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException(AuthenticationException e) {
        //
        log.warn(e.getMessage(), e);
        //
        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String logout(WebRequest webRequest) {
        //
        webRequest.removeAttribute("user", WebRequest.SCOPE_SESSION);
        //
        return "redirect:/login";
    }

    @RequestMapping("/register")
    public String register(@Validated(Register.class) ProjectUser user) {
        //
        projectUserService.createUser(user);
        //
        return "redirect:/login";
    }

    @ExceptionHandler({BindException.class, DuplicateKeyException.class})
    public ModelAndView handException(Exception e, WebRequest webRequest) {
        //
        Map<String, Object> exceptionModel = new HashMap<String, Object>();
        exceptionModel.put("exception", e);
        //
        String redirect = "redirect:";
        String requestPath = (String) webRequest.getAttribute("requestPath", WebRequest.SCOPE_REQUEST);
        if(requestPath.contains("/login")) {
            redirect = redirect + "/login";
        }
        if(requestPath.contains("/register")) {
            redirect = redirect + "/register";
        }
        //
        return new ModelAndView(redirect, exceptionModel);
    }

}
