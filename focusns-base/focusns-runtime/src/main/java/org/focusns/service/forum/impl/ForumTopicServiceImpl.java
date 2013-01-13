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
