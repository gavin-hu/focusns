/*
 * Copyright (C) 2011-2013 FocusSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

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
