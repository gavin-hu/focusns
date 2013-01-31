

package org.focusns.service.forum.impl;

import org.focusns.dao.forum.ForumPostDao;
import org.focusns.model.forum.ForumPost;
import org.focusns.service.forum.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ForumPostServiceImpl implements ForumPostService {

    @Autowired
    private ForumPostDao forumPostDao;

    @Override
    public ForumPost getForumPost(long postId) {
        return forumPostDao.select(postId);
    }

    @Override
    public void createForumPost(ForumPost post) {
        forumPostDao.insert(post);
    }

    @Override
    public void modifyForumPost(ForumPost post) {
        forumPostDao.update(post);
    }

    @Override
    public void removeForumPost(ForumPost post) {
        forumPostDao.delete(post.getId());
    }
}
