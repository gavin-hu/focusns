package org.focusns.event;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-event.xml", "/applicationContext-service.xml", "/applicationContext-dao.xml"})
public abstract class AbstractEventTest {
    
}
