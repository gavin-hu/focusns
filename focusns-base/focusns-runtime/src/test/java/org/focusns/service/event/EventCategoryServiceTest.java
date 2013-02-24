package org.focusns.service.event;

import java.util.Date;

import org.focusns.model.event.EventCategory;
import org.focusns.service.AbstractServiceTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
