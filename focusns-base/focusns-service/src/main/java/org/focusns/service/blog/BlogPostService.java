package org.focusns.service.blog;

import org.focusns.model.blog.BlogPost;
import org.focusns.model.common.Page;

public interface BlogPostService {

    BlogPost getBlogPost(long postId);
    
    void createBlogPost(BlogPost post);
    
    void modifyBlogPost(BlogPost post);
    
    void removeBlogPost(BlogPost post);
    
    Page<BlogPost> fetchPageByCategoryId(Page<BlogPost> page, long categoryId);

    Page<BlogPost> fetchPageByProjectId(Page<BlogPost> page, long projectId);
    
}
