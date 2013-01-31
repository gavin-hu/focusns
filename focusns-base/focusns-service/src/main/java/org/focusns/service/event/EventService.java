

package org.focusns.service.event;

import org.focusns.model.event.Event;

public interface EventService {

    Event getEvent(long eventId);

    void createEvent(Event event);

    void modifyEvent(Event event);

    void removeEvent(Event event);

}
