
package org.focusns.dao.blog;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.blog.BlogComment;
import org.focusns.model.common.Page;

public interface BlogCommentDao extends BaseDao<BlogComment> {

    Page<BlogComment> fetchByPostId(Page<BlogComment> page, long postId);
    
}
