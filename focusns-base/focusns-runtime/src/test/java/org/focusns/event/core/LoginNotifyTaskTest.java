package org.focusns.event.core;

import org.focusns.event.AbstractEventTest;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectUserService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class LoginNotifyTaskTest extends AbstractEventTest {
    
    @Autowired
    private ProjectUserService projectUserService;
    
    @Test
    public void testDo() {
        ProjectUser user = projectUserService.getUser("admin");
        System.out.println(user.getEmail());
    }
}
