package org.focusns.service.msg;

import org.focusns.model.common.Page;
import org.focusns.model.msg.Message;
import org.focusns.service.AbstractServiceTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class MessageServiceTest extends AbstractServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void testCreateMessage() {
        Message message = new Message();
        message.setTitle("xinxi");
        message.setContent("neirong");
        message.setCreateAt(new Date());
        message.setProjectId(1);
        message.setCreateById(1);
        message.setToProjectId(1);
        //
        messageService.createMessage(message);
    }

    @Test
    public void testFetchPage() {
        Page<Message> page = new Page(10);
        page = messageService.fetchPageByBox(page, "in", 1);
        System.out.println(page.getTotalCount());
    }

}
