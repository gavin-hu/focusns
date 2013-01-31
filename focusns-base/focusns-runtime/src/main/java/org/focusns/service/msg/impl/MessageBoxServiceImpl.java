
package org.focusns.service.msg.impl;

import org.focusns.dao.msg.MessageBoxDao;
import org.focusns.model.msg.MessageBox;
import org.focusns.service.msg.MessageBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageBoxServiceImpl implements MessageBoxService {

    @Autowired
    private MessageBoxDao messageBoxDao;

    @Override
    public void createMessageBox(MessageBox msgBox) {
        messageBoxDao.insert(msgBox);
    }

    @Override
    public void modifyMessageBox(MessageBox msgBox) {
        messageBoxDao.update(msgBox);
    }

    @Override
    public void removeMessageBox(MessageBox msgBox) {
        messageBoxDao.delete(msgBox.getId());
    }

    @Override
    public List<MessageBox> listMessageBoxes(long projectId) {
        return messageBoxDao.selectList(projectId);
    }
}
