package org.focusns.dao.core.impl;

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

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.model.core.ProjectUser;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProjectUserDaoImpl extends MyBatisBaseDao<ProjectUser> implements ProjectUserDao {

    @Override
    public ProjectUser selectWithProject(long id) {
        return getSqlSession().selectOne(NAMESPACE.concat(".selectWithProject"), id);
    }

    public ProjectUser selectByUsername(String username) {
        return getSqlSession().selectOne(NAMESPACE.concat(".selectByUsername"), username);
    }

    public void insertRole(long projectId, long userId, long roleId) {
        Map parameter = new HashMap();
        parameter.put("projectId", projectId);
        parameter.put("userId", userId);
        parameter.put("roleId", roleId);
        //
        getSqlSession().insert(NAMESPACE.concat(".insertRole"), parameter);
    }

    public void deleteRole(long projectId, long userId, long roleId) {
        Map parameter = new HashMap();
        parameter.put("projectId", projectId);
        parameter.put("userId", userId);
        parameter.put("roleId", roleId);
        //
        getSqlSession().delete(NAMESPACE.concat(".deleteRole"), roleId);
    }

}
