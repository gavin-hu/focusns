package org.focusns.dao.event.impl;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.event.EventCategoryDao;
import org.focusns.model.event.EventCategory;
import org.springframework.stereotype.Repository;

@Repository
public class EventCategoryDaoImpl extends MyBatisBaseDao<EventCategory> implements EventCategoryDao {
}
