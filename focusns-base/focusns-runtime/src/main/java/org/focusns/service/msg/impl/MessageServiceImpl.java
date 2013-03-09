package org.focusns.service.msg.impl;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */


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
