package org.focusns.service.msg;

import org.focusns.model.msg.MessageBox;
import org.focusns.service.AbstractServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class MessageBoxServiceTest extends AbstractServiceTest {

    @Autowired
    private MessageBoxService messageBoxService;

    @Test
    public void testCreateMessageBox() {
         MessageBox messageBox = new MessageBox();
         messageBox.setBox("IN");
         messageBox.setProjectId(1);
         messageBox.setCreateAt(new Date());
         messageBox.setCreateById(1);
         messageBoxService.createMessageBox(messageBox);
    }
}
