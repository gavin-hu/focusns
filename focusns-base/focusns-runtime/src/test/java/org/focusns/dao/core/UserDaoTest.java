package org.focusns.dao.core;


import org.focusns.dao.AbstractDaoTest;
import org.focusns.model.core.User;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class UserDaoTest extends AbstractDaoTest {

	@Autowired
	private UserDao userDao;
	
	@Test
	public void testInsert() {
		User user = new User();
		user.setUsername("haozhonghu");
		user.setPassword("123456");
		user.setEmail("haozhonghu@hotmail.com");
		//
		userDao.insert(user);
		Assert.assertTrue(user.getId()>0);
	}
	
	@Test
	public void testDelete() {
		User user = userDao.selectByUsername("haozhonghu");
		int count = userDao.delete(user.getId());
		Assert.assertTrue(count==1);
	}
	
}
