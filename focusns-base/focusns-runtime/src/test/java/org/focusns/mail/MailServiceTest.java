package org.focusns.mail;

import org.focusns.common.mail.MailService;
import org.focusns.model.core.ProjectUser;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext-support.xml")
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void send() throws Exception {
        //
        ProjectUser projectUser = new ProjectUser();
        projectUser.setEmail("focusns@sina.cn");
        //
        Map<String, Object> ctx = new HashMap<String, Object>();
        ctx.put("projectUser", projectUser);
        //
        mailService.send("signup-confirm", ctx);

    }

}
