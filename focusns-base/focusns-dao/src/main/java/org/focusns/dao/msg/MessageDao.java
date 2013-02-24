package org.focusns.dao.msg;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.common.Page;
import org.focusns.model.msg.Message;

public interface MessageDao extends BaseDao<Message> {

    Page<Message> fetchByProjectId(Page<Message> page, long projectId);

    Page<Message> fetchByToProjectId(Page<Message> page, long toProjectId);

}