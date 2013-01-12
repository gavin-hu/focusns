package org.focusns.dao.core;

import junit.framework.Assert;
import org.focusns.dao.AbstractDaoTest;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Ignore
public class ProjectDaoTest extends AbstractDaoTest {

	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private ProjectCategoryDao categoryDao;

	@Test
	public void setup() {
		ProjectCategory category = new ProjectCategory();
		category.setCode("People");
		int count = categoryDao.insert(category);
		Assert.assertEquals(1, count);
	}
	
	@Test
	public void testInsert() {
		ProjectCategory category = categoryDao.selectByCode("People");
		Assert.assertNotNull(category);
		//
		Date now = new Date();
		//
		Project project = new Project();
		project.setCode("gavin");
		project.setTitle("Gavin Hu");
		project.setDescription("This is the gavin's project!");
		project.setCreateAt(now);
		project.setModifyAt(now);
		project.setCategory(category);
		//
		int count = projectDao.insert(project);
		Assert.assertEquals(1, count);
	}
	
	@Test
	public void testSelectByCode() {
		Project project = projectDao.selectByCode("gavin");
		Assert.assertEquals("Gavin Hu", project.getTitle());
	}
	
	@Test
	public void testUpdate() {
		Project project = projectDao.selectByCode("gavin");
		project.setTitle("Aimy Wang");
		int count = projectDao.update(project);
		Assert.assertEquals(1, count);
		//
		project = projectDao.selectByCode("gavin");
		Assert.assertEquals("Aimy Wang", project.getTitle());
	}
	
	@Test 
	public void testDelete() {
		Project project = projectDao.selectByCode("gavin");
		int count = projectDao.delete(project.getId());
		Assert.assertEquals(1, count);
	}
	
	@Test
	public void destroy() {
		ProjectCategory category = categoryDao.selectByCode("People");
		int count = categoryDao.delete(category.getId());
		Assert.assertEquals(1, count);
	}
	
}
