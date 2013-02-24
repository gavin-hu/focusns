package org.focusns.dao.cms.impl;

import org.focusns.dao.cms.ArticleCategoryDao;
import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.model.cms.ArticleCategory;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleCategoryDaoImpl extends MyBatisBaseDao<ArticleCategory>
        implements ArticleCategoryDao {
}
