package org.focusns.common.web.page.engine.widget;

/*
 * #%L
 * FocusSNS Runtime
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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.focusns.common.web.page.config.PageConfig;
import org.focusns.common.web.page.config.PositionConfig;
import org.focusns.common.web.page.config.WidgetConfig;
import org.focusns.common.web.page.engine.PageEngine;
import org.focusns.common.web.page.engine.PageEngineException;
import org.focusns.common.web.page.engine.PageRenderInterceptor;
import org.focusns.common.web.widget.mvc.support.Navigator;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.AccessException;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.util.UrlPathHelper;

public class WidgetPageEngine extends WebApplicationObjectSupport implements PageEngine {

    private static final Log log = LogFactory.getLog(WidgetPageEngine.class);

    private static final String EVALUATION_CONTEXT_KEY = StandardEvaluationContext.class.getName();

    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    private ExpressionParser expressionParser = new SpelExpressionParser();

    private List<PageRenderInterceptor> pageRenderInterceptors = Collections.emptyList();
    private List<WidgetRenderInterceptor> widgetRenderInterceptors = Collections.emptyList();
    private List<WidgetActionInterceptor> widgetActionInterceptors = Collections.emptyList();

    // private Generator validationJsGenerator = new ValidationJsGenerator();

    public void setPageRenderInterceptors(List<PageRenderInterceptor> pageRenderInterceptors) {
        this.pageRenderInterceptors = pageRenderInterceptors;
    }

    public void setWidgetRenderInterceptors(List<WidgetRenderInterceptor> widgetRenderInterceptors) {
        this.widgetRenderInterceptors = widgetRenderInterceptors;
    }

    public void setWidgetActionInterceptors(List<WidgetActionInterceptor> widgetActionInterceptors) {
        this.widgetActionInterceptors = widgetActionInterceptors;
    }

    public void doRender(HttpServletRequest request, HttpServletResponse response) throws PageEngineException {
        //
        try {
            //
            if (request.getSession().getAttribute("redirectAttributes") != null) {
                Map<String, Object> redirectAttributes = (Map<String, Object>) request.getSession().getAttribute("redirectAttributes");
                for (String name : redirectAttributes.keySet()) {
                    Object value = redirectAttributes.get(name);
                    request.setAttribute(name, value);
                }
                // request.getSession().removeAttribute("redirectAttributes");
            }
            //
            Map<String, WidgetRequest> widgetRequestMap = new HashMap<String, WidgetRequest>();
            Map<String, WidgetResponse> widgetResponseMap = new HashMap<String, WidgetResponse>();
            //
            PageConfig pageConfig = (PageConfig) request.getAttribute("pageConfig");
            //
            for (PageRenderInterceptor pageRenderInterceptor : pageRenderInterceptors) {
                pageRenderInterceptor.beforeRender(request, response);
            }
            //
            for (String position : pageConfig.getPositionConfigMap().keySet()) {
                PositionConfig positionConfig = pageConfig.getPositionConfigMap().get(position);
                for (WidgetConfig widgetConfig : positionConfig.getWidgetConfigMap().values()) {
                    //
                    WidgetRequest widgetRequest = new WidgetRequest(request, widgetConfig, "render");
                    WidgetResponse widgetResponse = new WidgetResponse(response);
                    //
                    ServletContext widgetContext = getServletContext();
                    if (StringUtils.hasText(widgetConfig.getContext())) {
                        widgetContext = getServletContext().getContext(widgetConfig.getContext());
                        if (widgetContext == null) {
                            continue;
                        }
                    }
                    //
                    /*
                     * if(StringUtils.hasText(widgetConfig.getValidationTarget())
                     * ) { // Map<String, Object> context = new HashMap<String,
                     * Object>(); // Class<?> targetClass =
                     * ClassUtils.forName(widgetConfig.getValidationTarget());
                     * Class<?>[] groupClasses = new Class[]{};
                     * if(!widgetConfig.getValidationGroups().isEmpty()) {
                     * List<Class<?>> groupClassList = new
                     * ArrayList<Class<?>>(); for(String validationGroup :
                     * widgetConfig.getValidationGroups()) {
                     * groupClassList.add(ClassUtils.forName(validationGroup));
                     * } groupClassList.add(Default.class); groupClasses =
                     * groupClassList.toArray(new Class[groupClassList.size()]);
                     * } ValidatorFactory validatorFactory =
                     * getApplicationContext().getBean(ValidatorFactory.class);
                     * ValidatedBean validatedBean =
                     * ValidationHelper.createForClass(validatorFactory,
                     * targetClass, groupClasses); context.put("validatedBean",
                     * validatedBean); // context.put("widgetConfig",
                     * widgetConfig); String validationDir =
                     * getServletContext().
                     * getRealPath("/WEB-INF/generate/validation"); OutputStream
                     * output = new FileOutputStream(new File(validationDir,
                     * widgetConfig.getValidationTarget()+".js")); //
                     * validationJsGenerator.generate(context, output); }
                     */
                    //
                    for (WidgetRenderInterceptor widgetRenderInterceptor : widgetRenderInterceptors) {
                        widgetRenderInterceptor.beforeRender(request, response);
                    }
                    //
                    String widgetPath = "/widget" + widgetConfig.getTarget();
                    widgetContext.getRequestDispatcher(widgetPath).include(widgetRequest, widgetResponse);
                    //
                    for (WidgetRenderInterceptor widgetRenderInterceptor : widgetRenderInterceptors) {
                        widgetRenderInterceptor.afterRender(request, response);
                    }
                    //
                    widgetRequestMap.put(widgetConfig.getId(), widgetRequest);
                    widgetResponseMap.put(widgetConfig.getId(), widgetResponse);
                }
            }
            //
            request.setAttribute("widgetRequestMap", widgetRequestMap);
            request.setAttribute("widgetResponseMap", widgetResponseMap);
            //
            for (PageRenderInterceptor pageRenderInterceptor : pageRenderInterceptors) {
                pageRenderInterceptor.afterRender(request, response);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new PageEngineException(e.getMessage(), e);
        }
    }

    public void doAction(HttpServletRequest request, HttpServletResponse response) throws PageEngineException {
        //
        try {
            //
            PageConfig pageConfig = (PageConfig) request.getSession().getAttribute("pageConfig");
            String widgetId = (String) request.getAttribute("widgetId");
            WidgetConfig widgetConfig = pageConfig.getWidgetConfigById(widgetId);
            //
            WidgetRequest widgetRequest = new WidgetRequest(request, widgetConfig, "action");
            WidgetResponse widgetResponse = new WidgetResponse(response);
            //
            String lookupPath = urlPathHelper.getLookupPathForRequest(request);
            String queryString = urlPathHelper.getOriginatingQueryString(request);
            String actionPath = "/widget" + lookupPath + "?" + queryString;
            ServletContext widgetContext = getServletContext();
            if (StringUtils.hasText(widgetConfig.getContext())) {
                widgetContext = getServletContext().getContext(widgetConfig.getContext());
                if (widgetContext == null) {
                    return;
                }
            }
            //
            widgetRequest.setAttribute("requestType", "action");
            widgetRequest.setAttribute("widgetConfig", widgetConfig);
            //
            Navigator navigator = Navigator.get();
            widgetRequest.setAttribute("navigator", navigator);
            //
            for (WidgetActionInterceptor actionInterceptor : widgetActionInterceptors) {
                actionInterceptor.beforeAction(request, response);
            }
            widgetContext.getRequestDispatcher(actionPath).forward(widgetRequest, widgetResponse);
            //
            for (WidgetActionInterceptor actionInterceptor : widgetActionInterceptors) {
                actionInterceptor.afterAction(request, response);
            }
            //
            if (!StringUtils.hasText(navigator.getNavigateTo())) {
                widgetResponse.flushBuffer();
                return;
            }
            //
            String pathExpr = widgetConfig.getNavigationMap().get(navigator.getNavigateTo());
            if (StringUtils.hasText(pathExpr)) {
                Expression expression = expressionParser.parseExpression(pathExpr, ParserContext.TEMPLATE_EXPRESSION);
                EvaluationContext evaluationContext = createEvaluationContext();
                String path = (String) expression.getValue(evaluationContext, request);
                //
                request.getSession().setAttribute("redirectAttributes", navigator.getRedirectAttributes());
                response.sendRedirect(request.getContextPath() + path);
            }
            //
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new PageEngineException(e.getMessage(), e);
        } finally {
            Navigator.reset();
        }
    }

    private EvaluationContext createEvaluationContext() {
        StandardEvaluationContext evaluationContext = (StandardEvaluationContext) getServletContext().getAttribute(EVALUATION_CONTEXT_KEY);
        if (evaluationContext == null) {
            evaluationContext = new StandardEvaluationContext();
            evaluationContext.addPropertyAccessor(new NavigatorPropertyAccessor());
            evaluationContext.addPropertyAccessor(new ServletPropertyAccessor());
            evaluationContext.addPropertyAccessor(new MapAccessor());
            //
            getServletContext().setAttribute(EVALUATION_CONTEXT_KEY, evaluationContext);
        }
        return evaluationContext;
    }

    private class ServletPropertyAccessor implements PropertyAccessor {

        @Override
        public Class[] getSpecificTargetClasses() {
            return new Class[] { HttpServletRequest.class, HttpSession.class, ServletContext.class };
        }

        @Override
        public boolean canRead(EvaluationContext context, Object target, String name) throws AccessException {
            return target != null && findAttribute(target, name) != null;
        }

        @Override
        public TypedValue read(EvaluationContext context, Object target, String name) throws AccessException {
            Object value = findAttribute(target, name);
            return new TypedValue(value);
        }

        @Override
        public boolean canWrite(EvaluationContext context, Object target, String name) throws AccessException {
            return false;
        }

        @Override
        public void write(EvaluationContext context, Object target, String name, Object newValue)
                throws AccessException {
            throw new UnsupportedOperationException();
        }

        private Object findAttribute(Object target, String name) {
            Object value = null;
            if (value == null && target instanceof HttpServletRequest) {
                value = ((HttpServletRequest) target).getAttribute(name);
                if (value == null) {
                    target = ((HttpServletRequest) target).getSession();
                }
            }
            if (value == null && target instanceof HttpSession) {
                value = ((HttpSession) target).getAttribute(name);
                if (value == null) {
                    target = ((HttpServletRequest) target).getSession().getServletContext();
                }
            }
            if (value == null && target instanceof ServletContext) {
                value = ((ServletContext) target).getAttribute(name);
            }
            return value;
        }
    }

    private class NavigatorPropertyAccessor implements PropertyAccessor {

        @Override
        public Class[] getSpecificTargetClasses() {
            return null;
        }

        @Override
        public boolean canRead(EvaluationContext context, Object target, String name) throws AccessException {
            return Navigator.get().getRedirectAttributes().containsKey(name);
        }

        @Override
        public TypedValue read(EvaluationContext context, Object target, String name) throws AccessException {
            Object value = Navigator.get().getRedirectAttributes().get(name);
            return value == null ? TypedValue.NULL : new TypedValue(value);
        }

        @Override
        public boolean canWrite(EvaluationContext context, Object target, String name) throws AccessException {
            return false;
        }

        @Override
        public void write(EvaluationContext context, Object target, String name, Object newValue)
                throws AccessException {
            throw new UnsupportedOperationException();
        }
    }

}
