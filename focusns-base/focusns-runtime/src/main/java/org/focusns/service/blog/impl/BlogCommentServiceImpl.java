
package org.focusns.service.blog.impl;

import org.focusns.dao.blog.BlogCommentDao;
import org.focusns.model.blog.BlogComment;
import org.focusns.model.common.Page;
import org.focusns.service.blog.BlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlogCommentServiceImpl implements BlogCommentService {

    @Autowired
    private BlogCommentDao commentDao;
    
    public void createBlogComment(BlogComment comment) {
        commentDao.insert(comment);
    }

    public void modifyBlogComment(BlogComment comment) {
        commentDao.update(comment);
    }

    public void removeBlogComment(BlogComment comment) {
        commentDao.delete(comment.getId());
    }

    public Page<BlogComment> fetchPageByPostId(Page<BlogComment> page, long postId) {
        return commentDao.fetchByPostId(page, postId);
    }
    
}
