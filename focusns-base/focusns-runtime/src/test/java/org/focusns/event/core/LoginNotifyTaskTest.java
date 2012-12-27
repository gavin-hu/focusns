package org.focusns.event.core;

import org.focusns.model.core.User;
import org.focusns.service.core.UserService;
import org.focusns.event.AbstractEventTest;
import org.junit.Test;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class LoginNotifyTaskTest extends AbstractEventTest {
    
    @Autowired
    private UserService userService;
    
    @Test
    public void testDo() {
        User user = userService.getUser("admin");
        System.out.println(user.getEmail());
    }
}
