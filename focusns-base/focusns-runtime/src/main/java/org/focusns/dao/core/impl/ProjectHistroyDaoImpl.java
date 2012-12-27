/*
 * Copyright (C) 2012 FocuSNS.
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

import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.core.ProjectHistroyDao;
import org.focusns.model.common.Page;
import org.focusns.model.core.ProjectHistroy;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectHistroyDaoImpl extends MyBatisBaseDao<ProjectHistroy>
    implements ProjectHistroyDao {

    public Page<ProjectHistroy> fetchByProjectId(Page<ProjectHistroy> page, long projectId) {
        //
        Map parameter = new HashMap();
        parameter.put("projectId", projectId);
        //
        return fetchPage(".fetchByProjectId", page, parameter);
    }
    
}
