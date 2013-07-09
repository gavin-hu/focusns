package org.focusns.security.filter;


import org.apache.shiro.web.subject.WebSubject;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectUserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class UserFilter extends org.apache.shiro.web.filter.authc.UserFilter
    implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //
        WebSubject webSubject = (WebSubject) getSubject(request, response);
        //
        boolean isAllowed = super.isAccessAllowed(request, response, mappedValue);
        Object sessionUser = webSubject.getSession().getAttribute(ProjectUser.class.getName());
        if(isAllowed && sessionUser==null) {
            //
            ProjectUserService projectUserService = beanFactory.getBean(ProjectUserService.class);
            String username = webSubject.getPrincipal().toString();
            ProjectUser projectUser = projectUserService.getProjectUser(username);
            //
            webSubject.getSession().setAttribute("projectUser", projectUser);
            webSubject.getSession().setAttribute(ProjectUser.class.getName(), projectUser);
        }
        //
        return isAllowed;
    }
}
