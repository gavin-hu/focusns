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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.focusns.common.web.page.config.PageConfig;
import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.web.Keys;
import org.focusns.web.widget.Constraint;
import org.focusns.web.widget.Constraints;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

public class WidgetInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //
        if (WebUtils.isIncludeRequest(request)) {
            PageConfig pageConfig = (PageConfig) request.getAttribute("pageConfig");
            if (pageConfig != null && handler instanceof HandlerMethod) {
                //
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                //
                if (processConstraintsBeforeHandle(request, handlerMethod)) {
                    return false;
                }
            }
            //
        }
        //
        return true;
    }

    private boolean processConstraintsBeforeHandle(HttpServletRequest request, HandlerMethod handlerMethod) {
        Constraints constraints = handlerMethod.getMethodAnnotation(Constraints.class);
        if (constraints != null) {
            for (Constraint constraint : constraints.value()) {
                //
                if (Constraint.PROJECT_NOT_NULL == constraint) {
                    if (request.getAttribute(Keys.REQUEST_PROJECT) == null) {
                        return true;
                    }
                } else if (Constraint.PROJECT_USER_NOT_NULL == constraint) {
                    if (request.getAttribute(Keys.REQUEST_PROJECT_USER) == null) {
                        return true;
                    }
                } else if (Constraint.PROJECT_USER_IS_NULL == constraint) {
                    if (request.getAttribute(Keys.REQUEST_PROJECT_USER) != null) {
                        return true;
                    }
                } else if (Constraint.PROJECT_NOT_MY_PROFILE == constraint) {
                    Project project = (Project) request.getAttribute(Keys.REQUEST_PROJECT);
                    ProjectUser projectUser = (ProjectUser) request.getAttribute(Keys.SESSION_PROJECT_USER);
                    if (project == null || projectUser == null || projectUser.getProjectId() == project.getId()) {
                        return true;
                    }
                }
            }
        }
        //
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        //
        if (WebUtils.isIncludeRequest(request)) {
            PageConfig pageConfig = (PageConfig) request.getAttribute("pageConfig");
            if (pageConfig != null && handler instanceof HandlerMethod) {
                //
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                //
                processConstraintsAfterHandle(handlerMethod, modelAndView);
            }
        }
    }

    private void processConstraintsAfterHandle(HandlerMethod handlerMethod, ModelAndView modelAndView) {
        Constraints constraints = handlerMethod.getMethodAnnotation(Constraints.class);
        if (constraints != null) {
            for (Constraint constraint : constraints.value()) {
                //
                if (Constraint.PAGE_NOT_EMPTY == constraint) {
                    Page<?> page = (Page<?>) modelAndView.getModel().get(Page.KEY);
                    if (page == null || page.getResults().isEmpty()) {
                        modelAndView.setViewName("blank");
                    }
                }
            }
        }
    }

}
