
package org.focusns.dao.blog;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.blog.BlogCategory;

import java.util.List;

public interface BlogCategoryDao extends BaseDao<BlogCategory> {
    
    List<BlogCategory> selectByProjectId(long projectId);
    
}
