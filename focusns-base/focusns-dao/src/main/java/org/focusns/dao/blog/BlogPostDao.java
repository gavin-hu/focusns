package org.focusns.dao.blog;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.blog.BlogPost;
import org.focusns.model.common.Page;

public interface BlogPostDao extends BaseDao<BlogPost> {

    /**
     *
     * @param page
     * @param projectId  #See Project
     * @param categoryId #See BlogCategory
     * @return
     */
    Page<BlogPost> fetchByProjectAndCategoryId(Page<BlogPost> page, Long projectId, Long categoryId);
    
}
