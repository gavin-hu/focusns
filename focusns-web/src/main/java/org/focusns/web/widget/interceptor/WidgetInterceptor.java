package org.focusns.web.widget.interceptor;

/*
 * #%L
 * FocusSNS Web
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */


import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.web.portal.config.PageConfig;
import org.focusns.web.portal.config.PositionConfig;
import org.focusns.web.portal.config.WidgetConfig;
import org.focusns.web.widget.Constraint;
import org.focusns.web.widget.annotation.Constraints;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WidgetInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //
        if(WebUtils.isIncludeRequest(request)) {
            PageConfig pageConfig = (PageConfig) request.getAttribute("pageConfig");
            if (pageConfig!=null && handler instanceof HandlerMethod) {
                //
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                //
                if(processConstraintsBeforeHandle(request, handlerMethod)) {
                    return false;
                }
            }
            //
            String positionName = request.getParameter("position");
            String widgetId = request.getParameter("widget");
            if(StringUtils.hasText(positionName) && StringUtils.hasText(widgetId) && pageConfig!=null) {
                PositionConfig positionConfig = pageConfig.getPositionConfig(positionName);
                WidgetConfig widgetConfig = positionConfig.getWidgetConfig(widgetId);
                request.setAttribute("widgetConfig", widgetConfig);
            }
        }
        //
        return true;
    }

    private boolean processConstraintsBeforeHandle(HttpServletRequest request, HandlerMethod handlerMethod) {
        Constraints constraints = handlerMethod.getMethodAnnotation(Constraints.class);
        if(constraints!=null) {
            for(Constraint constraint : constraints.value()) {
                //
                if(Constraint.PROJECT_REQUIRED==constraint) {
                    if(request.getAttribute(Project.KEY)==null) {
                        return true;
                    }
                } else if(Constraint.PROJECT_USER_REQUIRED==constraint) {
                    if(request.getAttribute(ProjectUser.KEY)==null) {
                        return true;
                    }
                } else if(Constraint.PROJECT_NOT_MY_PROFILE==constraint) {
                    Project project = (Project) request.getAttribute(Project.KEY);
                    ProjectUser projectUser = (ProjectUser) request.getAttribute(ProjectUser.KEY);
                    if(project==null || projectUser==null || projectUser.getProjectId()== project.getId()) {
                        return true;
                    }
                }
            }
        }
        //
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //
        if(WebUtils.isIncludeRequest(request)) {
            PageConfig pageConfig = (PageConfig) request.getAttribute("pageConfig");
            if (pageConfig!=null && handler instanceof HandlerMethod) {
                //
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                //
                processConstraintsAfterHandle(handlerMethod, modelAndView);
            }
        }
        //
        String redirect = request.getParameter("redirect");
        if(StringUtils.hasText(redirect)) {
            String contextPath = request.getContextPath();
            if(contextPath.length()>1) {
                redirect = redirect.substring(contextPath.length());
            }
            //
            modelAndView.setViewName("redirect:".concat(redirect));
        }
        //
        if(modelAndView==null) {
            return ;
        }
        //X
        if(modelAndView.getViewName()!=null) {
            return ;
        }
    }

    private void processConstraintsAfterHandle(HandlerMethod handlerMethod, ModelAndView modelAndView) {
        Constraints constraints = handlerMethod.getMethodAnnotation(Constraints.class);
        if(constraints!=null) {
            for(Constraint constraint : constraints.value()) {
                //
                if(Constraint.PAGE_NOT_EMPTY == constraint) {
                    Page<?> page = (Page<?>) modelAndView.getModel().get(Page.KEY);
                    if(page==null || page.getResults().isEmpty()) {
                        modelAndView.setViewName("blank");
                    }
                }
            }
        }
    }
}