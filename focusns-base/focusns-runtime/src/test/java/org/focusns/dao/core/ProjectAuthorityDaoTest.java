

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
import org.focusns.model.core.ProjectAuthority;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class ProjectAuthorityDaoTest extends AbstractDaoTest {

    @Autowired
    private ProjectAuthorityDao projectAuthorityDao;

    @Test
    public void testInsert() {
         ProjectAuthority authority = new ProjectAuthority();
        authority.setCode("project-profile-view");
        authority.setDescription("查看主页模块");
        //
        this.projectAuthorityDao.insert(authority);
    }
}
