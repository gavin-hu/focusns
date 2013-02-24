package org.focusns.dao.blog;

import java.util.List;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.blog.BlogCategory;

public interface BlogCategoryDao extends BaseDao<BlogCategory> {
    
    List<BlogCategory> selectByProjectId(long projectId);
    
}
