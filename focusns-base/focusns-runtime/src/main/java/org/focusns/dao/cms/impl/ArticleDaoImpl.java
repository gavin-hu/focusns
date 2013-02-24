package org.focusns.dao.cms.impl;

import org.focusns.dao.cms.ArticleDao;
import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.model.cms.Article;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDaoImpl extends MyBatisBaseDao<Article> implements ArticleDao {
}
