
package org.focusns.dao.msg.impl;

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
import org.focusns.dao.msg.MessageDao;
import org.focusns.model.common.Page;
import org.focusns.model.msg.Message;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MessageDaoImpl extends MyBatisBaseDao<Message> implements MessageDao {

    public Page<Message> fetchByProjectId(Page<Message> page, long projectId) {
        //
        Map parameter = createParameter(projectId, null);
        //
        return fetchPage(".fetchByProjectId", page, parameter);
    }

    public Page<Message> fetchByToProjectId(Page<Message> page, long toProjectId) {
        //
        Map parameter = createParameter(null, toProjectId);
        //
        return fetchPage(".fetchByToProjectId", page, parameter);
    }

    private Map createParameter(Long projectId, Long toProjectId) {
        //
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("projectId", projectId);
        parameter.put("toProjectId", toProjectId);
        //
        return parameter;
    }

}
