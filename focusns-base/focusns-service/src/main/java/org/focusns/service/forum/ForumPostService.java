package org.focusns.service.forum;

import org.focusns.model.forum.ForumPost;

public interface ForumPostService {

    ForumPost getForumPost(long postId);

    void createForumPost(ForumPost post);

    void modifyForumPost(ForumPost post);

    void removeForumPost(ForumPost post);
}
