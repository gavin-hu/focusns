

package org.focusns.service.forum.impl;

import org.focusns.dao.forum.ForumTopicDao;
import org.focusns.model.forum.ForumTopic;
import org.focusns.service.forum.ForumTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ForumTopicServiceImpl implements ForumTopicService {

    @Autowired
    private ForumTopicDao forumTopicDao;

    @Override
    public ForumTopic getForumTopic(long topicId) {
        return forumTopicDao.select(topicId);
    }

    @Override
    public void createForumTopic(ForumTopic topic) {
        forumTopicDao.insert(topic);
    }

    @Override
    public void modifyForumTopic(ForumTopic topic) {
        forumTopicDao.update(topic);
    }

    @Override
    public void removeForumTopic(ForumTopic topic) {
        forumTopicDao.delete(topic.getId());
    }
}
