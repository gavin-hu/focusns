package org.focusns.service.event;

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

import java.util.Date;

import org.focusns.model.calendar.Event;
import org.focusns.service.AbstractServiceTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class EventServiceTest extends AbstractServiceTest {

    @Autowired
    private EventService eventService;

    @Test
    public void createEvent() {
        Event event = new Event();
        event.setTitle("event title");
        event.setContent("event content");
        event.setBegin(new Date());
        event.setEnd(new Date());
        event.setCreatedAt(new Date());
        event.setModifiedAt(new Date());
        event.setCreatedById(1);
        event.setModifiedById(1);
        event.setProjectId(1);
        event.setCategoryId(1);
        //
        eventService.createEvent(event);
    }
}
