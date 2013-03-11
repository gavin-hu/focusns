package org.focusns.service.event.impl;

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

    public EventCategory getEventCategory(long categoryId) {
        return eventCategoryDao.select(categoryId);
    }

    public void createEventCategory(EventCategory category) {
        this.eventCategoryDao.insert(category);
    }

    public void modifyEventCategory(EventCategory category) {
        this.eventCategoryDao.update(category);
    }

    public void removeEventCategory(EventCategory category) {
        this.eventCategoryDao.delete(category.getId());
    }
}
