
package org.focusns.dao.blog.impl;

import org.focusns.dao.blog.BlogCategoryDao;
import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.model.blog.BlogCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlogCategoryDaoImpl extends MyBatisBaseDao<BlogCategory> 
    implements BlogCategoryDao {

    public List<BlogCategory> selectByProjectId(long projectId) {
        return getSqlSession()
                .selectList(NAMESPACE.concat(".selectByProjectId"), projectId);
    }

}
