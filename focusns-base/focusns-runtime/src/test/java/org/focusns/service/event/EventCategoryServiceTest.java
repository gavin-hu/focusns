package org.focusns.service.event;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */


import org.focusns.model.event.EventCategory;
import org.focusns.service.AbstractServiceTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Ignore
public class EventCategoryServiceTest extends AbstractServiceTest {

    @Autowired
    private EventCategoryService eventCategoryService;

    @Test
    public void testCreateEventCategory() {
         EventCategory category = new EventCategory();
        category.setLabel("任务");
        category.setCreateAt(new Date());
        category.setProjectId(1);
        category.setCreateById(1);
        //
        eventCategoryService.createEventCategory(category);
    }

}
