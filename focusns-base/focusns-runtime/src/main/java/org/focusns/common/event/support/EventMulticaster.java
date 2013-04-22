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

import java.util.concurrent.Executor;

import org.focusns.common.event.annotation.Event;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.util.ClassUtils;

public class EventMulticaster extends SimpleApplicationEventMulticaster {

    private ApplicationListener eventListener = new EventListener();

    @Override
    public void multicastEvent(final ApplicationEvent appEvent) {
        if (ClassUtils.isAssignableValue(EventContext.class, appEvent)) {
            //
            Executor executor = getTaskExecutor();
            final EventContext eventContext = (EventContext) appEvent;
            Event event = eventContext.getEventHandler().getAnnotation(Event.class);
            //
            if (event.async()) {
                getTaskExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        eventListener.onApplicationEvent(eventContext);
                    }
                });
            } else {
                eventListener.onApplicationEvent(eventContext);
            }
        } else {
            //
            super.multicastEvent(appEvent);
        }
    }

}
