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
import org.focusns.dao.core.ProjectLinkDao;
import org.focusns.model.common.Page;
import org.focusns.model.core.ProjectLink;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProjectLinkDaoImpl extends MyBatisBaseDao<ProjectLink>
    implements ProjectLinkDao {

    public ProjectLink selectByFromAndToProjectId(long fromProjectId, long toProjectId) {
        //
        Map parameter = createParameter(fromProjectId, toProjectId, null, null);
        //
        return getSqlSession().selectOne(NAMESPACE.concat(".selectByFromAndToProjectId"), parameter);
    }

    public void deleteByFromAndToProjectId(long fromProjectId, long toProjectId) {
        //
        Map parameter = createParameter(fromProjectId, toProjectId, null, null);
        //
        getSqlSession().delete(NAMESPACE.concat(".deleteByFromAndToProjectId"), parameter);
    }

    public Page<ProjectLink> fetchByToProjectId(Page<ProjectLink> page, Long toProjectId, String category, Boolean mutual) {
        //
        Map parameter = createParameter(null, toProjectId, category, mutual);
        //
        return fetchPage(".fetchPage", page, parameter);
    }

    public Page<ProjectLink> fetchByFromProjectId(Page<ProjectLink> page, Long fromProjectId, String category, Boolean mutual) {
        //
        Map parameter = createParameter(fromProjectId, null, category, mutual);
        //
        return fetchPage(".fetchPage", page, parameter);
    }

    private Map createParameter(Long fromProjectId, Long toProjectId, String category, Boolean mutual) {
        Map parameter = new HashMap();
        //
        parameter.put("fromProjectId", fromProjectId);
        parameter.put("toProjectId", toProjectId);
        parameter.put("category", category);
        parameter.put("mutual", mutual);
        //
        return parameter;
    }
}
