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

import java.util.HashMap;
import java.util.Map;

import org.focusns.common.dao.MyBatisDaoSupport;
import org.focusns.dao.core.ProjectLinkDao;
import org.focusns.model.common.Page;
import org.focusns.model.core.ProjectLink;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectLinkDaoImpl extends MyBatisDaoSupport<ProjectLink> implements ProjectLinkDao {

    public ProjectLink selectByFromAndToProjectId(long fromProjectId, long toProjectId) {
        //
        Map parameter = createParameter(fromProjectId, toProjectId, null, null);
        //
        return selectOne("selectByFromAndToProjectId", parameter);
    }

    public void deleteByFromAndToProjectId(long fromProjectId, long toProjectId) {
        //
        Map parameter = createParameter(fromProjectId, toProjectId, null, null);
        //
        delete("deleteByFromAndToProjectId", parameter);
    }

    public Page<ProjectLink> selectByToProjectId(Page<ProjectLink> page, Long toProjectId, String category,
            Boolean mutual) {
        //
        Map parameter = createParameter(null, toProjectId, category, mutual);
        //
        return selectPage("selectPage", page, parameter);
    }

    public Page<ProjectLink> selectByFromProjectId(Page<ProjectLink> page, Long fromProjectId, String category,
            Boolean mutual) {
        //
        Map parameter = createParameter(fromProjectId, null, category, mutual);
        //
        return selectPage("selectPage", page, parameter);
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
