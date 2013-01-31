

package org.focusns.service.forum;

import org.focusns.model.forum.ForumTopic;
import org.focusns.service.AbstractServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ForumTopicServiceTest extends AbstractServiceTest {

    @Autowired
    private ForumTopicService forumTopicService;

    @Test
    public void createForumTopic() {
        ForumTopic topic = new ForumTopic();
        topic.setTitle("forum title");
        topic.setContent("forum content");
        topic.setCreateAt(new Date());
        topic.setModifyAt(new Date());
        topic.setCreateById(1);
        topic.setModifyById(1);
        topic.setProjectId(1);
        topic.setCategoryId(1);
        //
        forumTopicService.createForumTopic(topic);
    }

}
