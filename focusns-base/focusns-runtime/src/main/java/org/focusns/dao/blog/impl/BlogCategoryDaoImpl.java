package org.focusns.dao.blog.impl;

import java.util.List;

import org.focusns.dao.blog.BlogCategoryDao;
import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.model.blog.BlogCategory;
import org.springframework.stereotype.Repository;

@Repository
public class BlogCategoryDaoImpl extends MyBatisBaseDao<BlogCategory> 
    implements BlogCategoryDao {

    public List<BlogCategory> selectByProjectId(long projectId) {
        return getSqlSession()
                .selectList(NAMESPACE.concat(".selectByProjectId"), projectId);
    }

}
