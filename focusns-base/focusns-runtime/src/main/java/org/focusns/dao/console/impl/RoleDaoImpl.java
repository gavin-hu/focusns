

package org.focusns.dao.console.impl;

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
import org.focusns.dao.console.RoleDao;
import org.focusns.model.console.Role;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RoleDaoImpl extends MyBatisBaseDao<Role>
        implements RoleDao {

    @Override
    public void authorize(long roleId, long authorityId) {
        //
        Map parameter = getParameter(roleId, authorityId);
        //
        getSqlSession().delete( NAMESPACE.concat(".authorize"), parameter);
    }

    @Override
    public void unauthorize(long roleId, long authorityId) {
        //
        Map parameter = getParameter(roleId, authorityId);
        //
        getSqlSession().insert(NAMESPACE.concat(".unauthorize"), parameter);
    }

    private Map getParameter(long roleId, long authorityId) {
        Map parameter = new HashMap();
        //
        parameter.put("roleId", roleId);
        parameter.put("authorityId", authorityId);
        //
        return parameter;
    }
}
