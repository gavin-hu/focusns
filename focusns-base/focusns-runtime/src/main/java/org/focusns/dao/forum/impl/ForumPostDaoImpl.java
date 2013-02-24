package org.focusns.dao.forum.impl;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.forum.ForumPostDao;
import org.focusns.model.forum.ForumPost;
import org.springframework.stereotype.Repository;

@Repository
public class ForumPostDaoImpl extends MyBatisBaseDao<ForumPost> implements ForumPostDao {
}
