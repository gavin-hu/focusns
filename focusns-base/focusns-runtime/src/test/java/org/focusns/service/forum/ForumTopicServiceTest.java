package org.focusns.service.forum;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import java.util.Date;

import org.focusns.model.forum.ForumTopic;
import org.focusns.service.AbstractServiceTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class ForumTopicServiceTest extends AbstractServiceTest {

    @Autowired
    private ForumTopicService forumTopicService;

    @Test
    public void createForumTopic() {
        ForumTopic topic = new ForumTopic();
        topic.setTitle("forum title");
        topic.setContent("forum content");
        topic.setCreatedAt(new Date());
        topic.setModifiedAt(new Date());
        topic.setCreatedById(1);
        topic.setModifiedById(1);
        topic.setProjectId(1);
        topic.setCategoryId(1);
        //
        forumTopicService.createForumTopic(topic);
    }

}
