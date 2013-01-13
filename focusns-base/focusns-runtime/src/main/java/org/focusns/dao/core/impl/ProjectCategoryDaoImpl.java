/*
 * Copyright (C) 2011-2013 FocusSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.focusns.dao.core.impl;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.core.ProjectCategoryDao;
import org.focusns.model.core.ProjectCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectCategoryDaoImpl extends MyBatisBaseDao<ProjectCategory> implements ProjectCategoryDao {

	public ProjectCategory selectByCode(String code) {
		return getSqlSession().selectOne(NAMESPACE.concat(".selectByCode"), code);
	}

    public List<ProjectCategory> selectList(boolean isPublic) {
        return getSqlSession().selectList(NAMESPACE.concat(".selectList"), !isPublic);
    }
    
}
