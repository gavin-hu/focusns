package org.focusns.security.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.subject.WebSubject;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectUserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class DefaultAuthenticationListener implements AuthenticationListener, BeanFactoryAware {

    private static final Log log = LogFactory.getLog(DefaultAuthenticationListener.class);

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    protected ProjectUserService getProjectUserService() {
        return beanFactory.getBean(ProjectUserService.class);
    }

    @Override
    public void onSuccess(AuthenticationToken token, AuthenticationInfo info) {
        //
        log.info("User " + token.getPrincipal() + " login success!");
        WebSubject webSubject = (WebSubject) SecurityUtils.getSubject();
        Session session = webSubject.getSession();
        //
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        ProjectUser projectUser = getProjectUserService().getUser(usernamePasswordToken.getUsername());
        session.setAttribute("projectUser", projectUser);
    }

    @Override
    public void onFailure(AuthenticationToken token, AuthenticationException ae) {
        log.warn(ae.getMessage());
    }

    @Override
    public void onLogout(PrincipalCollection principals) {
        log.info("User " + principals.getPrimaryPrincipal() + " logout success!");
    }
}
