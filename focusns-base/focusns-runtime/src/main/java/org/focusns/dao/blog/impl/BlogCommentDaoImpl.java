
package org.focusns.dao.blog.impl;

import org.focusns.dao.blog.BlogCommentDao;
import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.model.blog.BlogComment;
import org.focusns.model.common.Page;
import org.springframework.stereotype.Repository;

@Repository
public class BlogCommentDaoImpl extends MyBatisBaseDao<BlogComment> 
    implements BlogCommentDao {

    public Page<BlogComment> fetchByPostId(Page<BlogComment> page, long postId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
