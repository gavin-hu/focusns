

package org.focusns.service.event.impl;

import org.focusns.dao.event.EventDao;
import org.focusns.model.event.Event;
import org.focusns.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    @Override
    public Event getEvent(long eventId) {
        return eventDao.select(eventId);
    }

    @Override
    public void createEvent(Event event) {
        this.eventDao.insert(event);
    }

    @Override
    public void modifyEvent(Event event) {
        this.eventDao.update(event);
    }

    @Override
    public void removeEvent(Event event) {
        this.eventDao.delete(event.getId());
    }
}
