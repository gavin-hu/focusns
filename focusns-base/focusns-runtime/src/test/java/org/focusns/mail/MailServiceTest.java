package org.focusns.mail;

import org.focusns.common.mail.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext-support.xml")
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void send() {
        mailService.send("text", new HashMap<String, Object>());
    }

}
