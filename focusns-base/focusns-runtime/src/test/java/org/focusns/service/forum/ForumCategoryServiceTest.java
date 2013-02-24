package org.focusns.service.forum;

import java.util.Date;

import org.focusns.model.forum.ForumCategory;
import org.focusns.service.AbstractServiceTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class ForumCategoryServiceTest extends AbstractServiceTest {

    @Autowired
    private ForumCategoryService forumCategoryService;

    @Test
    public void testCreateForumCategory() {
        ForumCategory category = new ForumCategory();
        category.setLabel("Java 技术");
        category.setCreateAt(new Date());
        category.setProjectId(1);
        category.setCreateById(1);
        //
        forumCategoryService.createForumCategory(category);
    }

}
