

package org.focusns.dao.event.impl;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.event.EventDao;
import org.focusns.model.event.Event;
import org.springframework.stereotype.Repository;

@Repository
public class EventDaoImpl extends MyBatisBaseDao<Event> implements EventDao {
}
