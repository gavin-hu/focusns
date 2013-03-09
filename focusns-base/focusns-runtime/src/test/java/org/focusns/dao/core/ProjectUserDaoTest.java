package org.focusns.dao.core;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */


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
