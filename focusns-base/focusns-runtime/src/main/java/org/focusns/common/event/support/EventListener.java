package org.focusns.common.event.support;

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

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.focusns.common.event.annotation.Event;
import org.focusns.common.event.annotation.EventSubscriber;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.CannotLoadBeanClassException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

public class EventListener implements ApplicationListener<EventContext> {

    private static final Log log = LogFactory.getLog(EventListener.class);

    public void onApplicationEvent(EventContext eventContext) {
        //
        Event event = eventContext.getEventHandler().getAnnotation(Event.class);
        //
        if(log.isDebugEnabled()) {
            log.debug(String.format("Event %s triggered!", event.on()));
        }
        // invoke event handle method
        Object eventSubscriber = eventContext.getEventSubscriber();
        //
        AutowireCapableBeanFactory beanFactory = eventContext.getApplicationContext().getAutowireCapableBeanFactory();
        beanFactory.autowireBeanProperties(eventSubscriber, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
        Method eventHandler = eventContext.getEventHandler();
        ReflectionUtils.invokeMethod(eventHandler, eventSubscriber, eventContext);
    }

}
