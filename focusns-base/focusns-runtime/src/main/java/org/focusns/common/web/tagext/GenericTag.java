package org.focusns.common.web.tagext;

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

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public abstract class GenericTag extends TagSupport {

    private boolean initialized = false;
    private WebApplicationContext controlContext;
    //
    protected BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(this);

    public WebApplicationContext getControlContext() {
        return controlContext;
    }

    @Override
    public void setPageContext(PageContext pageContext) {
        super.setPageContext(pageContext);
        //
        if (!initialized) {
            initialize();
            //
            initialized = true;
        }
    }

    private void initialize() {
        //
        this.controlContext = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
        //
        for (PropertyDescriptor pd : beanWrapper.getPropertyDescriptors()) {
            Method writeMethod = pd.getWriteMethod();
            if (writeMethod == null) {
                continue;
            }
            //
            Autowired autowired = writeMethod.getAnnotation(Autowired.class);
            if (autowired != null) {
                Object value = null;
                Qualifier qualifier = writeMethod.getAnnotation(Qualifier.class);
                try {
                    if (qualifier == null) {
                        value = controlContext.getBean(writeMethod.getParameterTypes()[0]);
                    } else {
                        value = controlContext.getBean(qualifier.value(), writeMethod.getParameterTypes()[0]);
                    }
                } catch (NoSuchBeanDefinitionException e) {
                    if (autowired.required()) {
                        throw e;
                    }
                }
                //
                if (value != null) {
                    beanWrapper.setPropertyValue(pd.getName(), value);
                }
            }
        }
    }
}
