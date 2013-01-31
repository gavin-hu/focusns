
package org.focusns.service.blog.impl;

import org.focusns.dao.blog.BlogPostDao;
import org.focusns.model.blog.BlogPost;
import org.focusns.model.common.Page;
import org.focusns.service.blog.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private BlogPostDao postDao;

    @Override
    public BlogPost getBlogPost(long postId) {
        return postDao.select(postId);
    }

    public void createBlogPost(BlogPost post) {
        postDao.insert(post);
    }

    public void modifyBlogPost(BlogPost post) {
        postDao.update(post);
    }

    public void removeBlogPost(BlogPost post) {
        postDao.delete(post.getId());
    }

    public Page<BlogPost> fetchPageByCategoryId(Page<BlogPost> page, long categoryId) {
        return postDao.fetchByProjectAndCategoryId(page, null, categoryId);
    }

    public Page<BlogPost> fetchPageByProjectId(Page<BlogPost> page, long projectId) {
        return postDao.fetchByProjectAndCategoryId(page, projectId, null);
    }
    
}
