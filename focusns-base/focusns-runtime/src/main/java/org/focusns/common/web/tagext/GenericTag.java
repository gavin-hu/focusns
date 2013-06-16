package org.focusns.common.web.tagext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

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
        if(!initialized) {
            initialize();
            //
            initialized = true;
        }
    }

    private void initialize() {
        //
        this.controlContext = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
        //
        for(PropertyDescriptor pd : beanWrapper.getPropertyDescriptors()) {
            Method writeMethod = pd.getWriteMethod();
            if(writeMethod==null) {
                continue;
            }
            //
            Autowired autowired = writeMethod.getAnnotation(Autowired.class);
            if(autowired!=null) {
                Object value = null;
                Qualifier qualifier = writeMethod.getAnnotation(Qualifier.class);
                try {
                    if(qualifier==null) {
                        value = controlContext.getBean(writeMethod.getParameterTypes()[0]);
                    } else {
                        value = controlContext.getBean(qualifier.value(), writeMethod.getParameterTypes()[0]);
                    }
                } catch (NoSuchBeanDefinitionException e) {
                    if(autowired.required()) {
                        throw e;
                    }
                }
                //
                if(value!=null) {
                    beanWrapper.setPropertyValue(pd.getName(), value);
                }
            }
        }
    }
}
