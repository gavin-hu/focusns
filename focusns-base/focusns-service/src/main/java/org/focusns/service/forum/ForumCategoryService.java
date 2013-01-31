

package org.focusns.service.forum;

import org.focusns.model.forum.ForumCategory;

public interface ForumCategoryService {

    ForumCategory getForumCategory(long categoryId);

    void createForumCategory(ForumCategory category);

    void modifyForumCategory(ForumCategory category);

    void removeForumCategory(ForumCategory category);

}
