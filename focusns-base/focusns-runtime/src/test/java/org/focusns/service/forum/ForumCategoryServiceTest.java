/*
 * Copyright (C) 2011-2013 FocusSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

package org.focusns.service.forum;

import org.focusns.model.forum.ForumCategory;
import org.focusns.service.AbstractServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

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
