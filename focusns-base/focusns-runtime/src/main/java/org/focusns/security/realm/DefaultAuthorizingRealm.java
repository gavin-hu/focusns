package org.focusns.security.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.subject.WebSubject;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectAuthority;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectAuthorityService;
import org.focusns.service.core.ProjectRoleService;
import org.focusns.service.core.ProjectUserService;
import org.focusns.service.team.TeamMemberService;
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
        ProjectUser projectUser = projectUserService.getProjectUser(usernamePasswordToken.getUsername());
        //
        Assert.notNull(projectUser,String.format("User not found for username %s", usernamePasswordToken.getUsername()));
        //
        return new SimpleAuthenticationInfo(projectUser.getUsername(), projectUser.getPassword(), REALM_NAME);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //
        TeamMemberService teamMemberService = beanFactory.getBean(TeamMemberService.class);
        ProjectUserService projectUserService = beanFactory.getBean(ProjectUserService.class);
        ProjectRoleService projectRoleService = beanFactory.getBean(ProjectRoleService.class);
        ProjectAuthorityService projectAuthorityService = beanFactory.getBean(ProjectAuthorityService.class);
        //
        Project project = getProjectFromWebSubject();
        ProjectUser projectUser = getProjectUserFromWebSubject();
        if(project==null) {

        }
        //

        //
        return null;
    }

    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        //
        Project project = getProjectFromWebSubject();
        if(project == null) {
            return super.getAuthorizationCacheKey(principals);
        }
        return principals.getPrimaryPrincipal().toString() + "#" + project.getId();
    }

    private Project getProjectFromWebSubject() {
        WebSubject webSubject = (WebSubject) SecurityUtils.getSubject();
        return (Project) webSubject.getServletRequest().getAttribute("project");
    }

    private ProjectUser getProjectUserFromWebSubject() {
        WebSubject webSubject = (WebSubject) SecurityUtils.getSubject();
        return (ProjectUser) webSubject.getSession().getAttribute("projectUser");
    }

}
