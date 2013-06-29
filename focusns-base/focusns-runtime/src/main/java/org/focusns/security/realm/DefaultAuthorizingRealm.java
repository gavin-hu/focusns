package org.focusns.security.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectUserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.util.Assert;

public class DefaultAuthorizingRealm extends AuthorizingRealm implements BeanFactoryAware {

    private static final String REALM_NAME = "default";

    private BeanFactory beanFactory;
    //
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //
        ProjectUserService projectUserService = beanFactory.getBean(ProjectUserService.class);
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        Assert.hasText(usernamePasswordToken.getUsername(), "Username can not be null!");
        ProjectUser projectUser = projectUserService.getUser(usernamePasswordToken.getUsername());
        //
        Assert.notNull(projectUser,String.format("User not found for username %s", usernamePasswordToken.getUsername()));
        //
        return new SimpleAuthenticationInfo(projectUser.getUsername(), projectUser.getPassword(), REALM_NAME);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

}
