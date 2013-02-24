package org.focusns.dao.forum.impl;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.forum.ForumTopicDao;
import org.focusns.model.forum.ForumTopic;
import org.springframework.stereotype.Repository;

@Repository
public class ForumTopicDaoImpl extends MyBatisBaseDao<ForumTopic> implements ForumTopicDao {
}
