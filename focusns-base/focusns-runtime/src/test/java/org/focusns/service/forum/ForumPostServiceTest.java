

package org.focusns.service.forum;

import org.focusns.model.forum.ForumPost;
import org.focusns.service.AbstractServiceTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
@Ignore
public class ForumPostServiceTest extends AbstractServiceTest {

    @Autowired
    private ForumPostService forumPostService;

    @Test
    public void createForumPost() {
        ForumPost post = new ForumPost();
        post.setTitle("post title");
        post.setContent("post content");
        post.setCreateAt(new Date());
        post.setModifyAt(new Date());
        post.setCreateById(1);
        post.setModifyById(1);
        post.setProjectId(1);
        post.setTopicId(1);
        //
        forumPostService.createForumPost(post);
    }

}
