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
