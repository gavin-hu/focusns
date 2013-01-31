
package org.focusns.dao.msg;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.msg.MessageBox;

import java.util.List;

public interface MessageBoxDao extends BaseDao<MessageBox> {

    List<MessageBox> selectList(long projectId);
}
