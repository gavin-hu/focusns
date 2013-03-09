package org.focusns.dao.core.impl;

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


import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.core.ProjectRoleDao;
import org.focusns.model.core.ProjectRole;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProjectRoleDaoImpl extends MyBatisBaseDao<ProjectRole> implements ProjectRoleDao {

    public void insertAuthority(long projectId, long roleId, long authorityId) {
        Map parameter = new HashMap();
        parameter.put("projectId", projectId);
        parameter.put("roleId", roleId);
        parameter.put("authorityId", authorityId);
        //
        getSqlSession().insert(NAMESPACE.concat(".insertAuthority"), parameter);
    }

    public void deleteAuthority(long projectId, long roleId, long authorityId) {
        Map parameter = new HashMap();
        parameter.put("projectId", projectId);
        parameter.put("roleId", roleId);
        parameter.put("authorityId", authorityId);
        //
        getSqlSession().delete(NAMESPACE.concat(".deleteAuthority"), parameter);
    }
}
