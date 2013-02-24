package org.focusns.dao.core;

import org.focusns.dao.AbstractDaoTest;
import org.focusns.model.core.ProjectUser;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class ProjectUserDaoTest extends AbstractDaoTest {

	@Autowired
	private ProjectUserDao projectUserDao;
	
	@Test
	public void testInsert() {
		ProjectUser user = new ProjectUser();
		user.setUsername("haozhonghu");
		user.setPassword("123456");
		user.setEmail("haozhonghu@hotmail.com");
		//
		projectUserDao.insert(user);
		Assert.assertTrue(user.getId()>0);
	}
	
	@Test
	public void testDelete() {
        ProjectUser user = projectUserDao.selectByUsername("haozhonghu");
		int count = projectUserDao.delete(user.getId());
		Assert.assertTrue(count==1);
	}

    @Test
    public void testInsertRole() {
        projectUserDao.insertRole(1, 1, 1);
    }

    @Test
    public void testDeleteRole() {
        projectUserDao.deleteRole(1, 1, 1);
    }
	
}
