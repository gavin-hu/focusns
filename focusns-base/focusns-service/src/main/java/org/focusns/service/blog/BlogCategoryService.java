package org.focusns.service.blog;

import org.focusns.model.blog.BlogCategory;

import java.util.List;

public interface BlogCategoryService {
    
    void createBlogCategory(BlogCategory tag);
    
    void modifyBlogCategory(BlogCategory tag);
    
    void removeBlogCategory(BlogCategory tag);
    
    List<BlogCategory> getBlogCategories(long projectId);
    
}
