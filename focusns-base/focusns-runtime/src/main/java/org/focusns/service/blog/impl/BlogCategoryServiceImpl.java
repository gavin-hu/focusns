
package org.focusns.service.blog.impl;

import org.focusns.dao.blog.BlogCategoryDao;
import org.focusns.model.blog.BlogCategory;
import org.focusns.service.blog.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @Autowired
    private BlogCategoryDao tagDao;
    
    public void createBlogCategory(BlogCategory tag) {
        tagDao.insert(tag);
    }

    public void modifyBlogCategory(BlogCategory tag) {
        tagDao.update(tag);
    }

    public void removeBlogCategory(BlogCategory tag) {
        tagDao.delete(tag.getId());
    }

    public List<BlogCategory> getBlogCategories(long projectId) {
        return tagDao.selectByProjectId(projectId);
    }
    
}
