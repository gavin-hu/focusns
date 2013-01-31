
package org.focusns.service.blog;

import org.focusns.model.blog.BlogComment;
import org.focusns.model.common.Page;

public interface BlogCommentService {

    void createBlogComment(BlogComment comment);
    
    void modifyBlogComment(BlogComment comment);
    
    void removeBlogComment(BlogComment comment);
    
    Page<BlogComment> fetchPageByPostId(Page<BlogComment> page, long postId);
}
