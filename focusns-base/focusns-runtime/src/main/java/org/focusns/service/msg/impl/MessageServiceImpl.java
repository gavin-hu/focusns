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
package org.focusns.service.msg.impl;

import org.focusns.dao.msg.MessageDao;
import org.focusns.model.common.Page;
import org.focusns.model.msg.Message;
import org.focusns.service.msg.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;
        
    public Message getMessage(long messageId) {
        return messageDao.select(messageId);
    }

    public void createMessage(Message message) {
        messageDao.insert(message);
    }

    public void modifyMessage(Message message) {
        messageDao.update(message);
    }

    public void removeMessage(Message message) {
        messageDao.delete(message.getId());
    }

    public Page<Message> fetchPageByBox(Page<Message> page, String box, long projectId) {
        //
        if("IN".equalsIgnoreCase(box)) {
            page = messageDao.fetchByToProjectId(page, projectId);
        }
        if("SENT".equalsIgnoreCase(box)) {
            page = messageDao.fetchByProjectId(page, projectId);
        }
        //
        return page;
    }
    
}
