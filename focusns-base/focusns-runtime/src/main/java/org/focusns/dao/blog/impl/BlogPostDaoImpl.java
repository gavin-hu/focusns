
package org.focusns.dao.blog.impl;

import org.focusns.dao.blog.BlogPostDao;
import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.model.blog.BlogPost;
import org.focusns.model.common.Page;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BlogPostDaoImpl extends MyBatisBaseDao<BlogPost> implements BlogPostDao {

    public Page<BlogPost> fetchByProjectAndCategoryId(Page<BlogPost> page, Long projectId, Long categoryId) {
        //
        Map parameter = new HashMap();
        parameter.put("projectId", projectId);
        parameter.put("categoryId", categoryId);
        //
        return fetchPage(".fetchByProjectAndCategoryId", page, parameter);
    }
}
