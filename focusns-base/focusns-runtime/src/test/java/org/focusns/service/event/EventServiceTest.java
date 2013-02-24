package org.focusns.service.event;

import java.util.Date;

import org.focusns.model.event.Event;
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
        event.setCreateAt(new Date());
        event.setModifyAt(new Date());
        event.setCreateById(1);
        event.setModifyById(1);
        event.setProjectId(1);
        event.setCategoryId(1);
        //
        eventService.createEvent(event);
    }
}
