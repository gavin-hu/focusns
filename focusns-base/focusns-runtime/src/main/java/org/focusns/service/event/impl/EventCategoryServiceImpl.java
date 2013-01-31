

package org.focusns.service.event.impl;

import org.focusns.dao.event.EventCategoryDao;
import org.focusns.model.event.EventCategory;
import org.focusns.service.event.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventCategoryServiceImpl implements EventCategoryService {

    @Autowired
    private EventCategoryDao eventCategoryDao;

    @Override
    public EventCategory getEventCategory(long categoryId) {
        return eventCategoryDao.select(categoryId);
    }

    @Override
    public void createEventCategory(EventCategory category) {
        this.eventCategoryDao.insert(category);
    }

    @Override
    public void modifyEventCategory(EventCategory category) {
        this.eventCategoryDao.update(category);
    }

    @Override
    public void removeEventCategory(EventCategory category) {
        this.eventCategoryDao.delete(category.getId());
    }
}
