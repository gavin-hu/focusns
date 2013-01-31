
package org.focusns.dao.msg.impl;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.msg.MessageBoxDao;
import org.focusns.model.msg.MessageBox;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageBoxDaoImpl extends MyBatisBaseDao<MessageBox> 
    implements MessageBoxDao {

    @Override
    public List<MessageBox> selectList(long projectId) {
        return getSqlSession().selectList(NAMESPACE.concat(".selectList"), projectId);
    }

}
