package org.focusns.dao.cms.impl;

import org.focusns.dao.cms.ArticleKeywordDao;
import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.model.cms.ArticleKeyword;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleKeywordDaoImpl extends MyBatisBaseDao<ArticleKeyword>
    implements ArticleKeywordDao {
}
