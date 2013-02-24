package org.focusns.dao.msg;

import java.util.List;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.msg.MessageBox;

public interface MessageBoxDao extends BaseDao<MessageBox> {

    List<MessageBox> selectList(long projectId);
}
