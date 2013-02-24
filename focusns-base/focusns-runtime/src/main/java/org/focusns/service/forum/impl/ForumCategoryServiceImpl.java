package org.focusns.service.forum.impl;

import org.focusns.dao.forum.ForumCategoryDao;
import org.focusns.model.forum.ForumCategory;
import org.focusns.service.forum.ForumCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ForumCategoryServiceImpl implements ForumCategoryService {

    @Autowired
    private ForumCategoryDao forumCategoryDao;

    public ForumCategory getForumCategory(long categoryId) {
        return forumCategoryDao.select(categoryId);
    }

    public void createForumCategory(ForumCategory category) {
        forumCategoryDao.insert(category);
    }

    public void modifyForumCategory(ForumCategory category) {
        forumCategoryDao.update(category);
    }

    public void removeForumCategory(ForumCategory category) {
        forumCategoryDao.delete(category.getId());
    }
}
