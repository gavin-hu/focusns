

package org.focusns.service.event;

import org.focusns.model.event.EventCategory;

public interface EventCategoryService {

    EventCategory getEventCategory(long categoryId);

    void createEventCategory(EventCategory category);

    void modifyEventCategory(EventCategory category);

    void removeEventCategory(EventCategory category);

}
