package org.focusns.dao.core;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */


import org.focusns.dao.AbstractDaoTest;
import org.focusns.model.core.ProjectCategory;
import org.junit.Assert;
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
