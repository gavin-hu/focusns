package org.focusns.dao.cms.impl;

import org.focusns.dao.cms.ArticleCommentDao;
import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.model.cms.ArticleComment;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleCommentDaoImpl extends MyBatisBaseDao<ArticleComment>
    implements ArticleCommentDao {
}
