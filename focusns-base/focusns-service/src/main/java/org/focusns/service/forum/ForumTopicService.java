package org.focusns.service.forum;

import org.focusns.model.forum.ForumTopic;

public interface ForumTopicService {

    ForumTopic getForumTopic(long topicId);

    void createForumTopic(ForumTopic topic);

    void modifyForumTopic(ForumTopic topic);

    void removeForumTopic(ForumTopic topic);
}
