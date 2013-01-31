

package org.focusns.dao.console;

import org.focusns.dao.AbstractDaoTest;
import org.focusns.model.console.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoTest extends AbstractDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("focusns");
        user.setPassword("123456");
        userDao.insert(user);
    }

}
