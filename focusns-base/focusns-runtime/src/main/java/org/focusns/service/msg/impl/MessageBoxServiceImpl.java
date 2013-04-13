package org.focusns.service.msg.impl;

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

    public void createMessageBox(MessageBox msgBox) {
        messageBoxDao.insert(msgBox);
    }

    public void modifyMessageBox(MessageBox msgBox) {
        messageBoxDao.update(msgBox);
    }

    public void removeMessageBox(MessageBox msgBox) {
        messageBoxDao.delete(msgBox.getId());
    }

    public List<MessageBox> listMessageBoxes(long projectId) {
        return messageBoxDao.selectList(projectId);
    }
}
