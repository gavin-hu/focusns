package org.focusns.dao.msg.impl;

import java.util.HashMap;
import java.util.Map;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.msg.MessageDao;
import org.focusns.model.common.Page;
import org.focusns.model.msg.Message;
import org.springframework.stereotype.Repository;

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
