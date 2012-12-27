package org.focusns.dao.core;

import junit.framework.Assert;

import org.focusns.dao.AbstractDaoTest;
import org.focusns.model.core.ProjectCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class ProjectCategoryDaoTest extends AbstractDaoTest {

	@Autowired
	private ProjectCategoryDao categoryDao;
	
	@Test
	public void testInsert() {
		ProjectCategory category = new ProjectCategory();
		category.setCode("People");
		category.setLabel("人员");
		//
		int count = categoryDao.insert(category);
		Assert.assertEquals(1, count);
	}
	
	@Test
	public void testDelete() {
		ProjectCategory category = categoryDao.selectByCode("People");
		int count = categoryDao.delete(category.getId());
		Assert.assertEquals(1, count);
	}
	
}
