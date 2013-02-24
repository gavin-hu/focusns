package org.focusns.dao.msg.impl;

import java.util.List;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.msg.MessageBoxDao;
import org.focusns.model.msg.MessageBox;
import org.springframework.stereotype.Repository;

@Repository
public class MessageBoxDaoImpl extends MyBatisBaseDao<MessageBox> 
    implements MessageBoxDao {

    public List<MessageBox> selectList(long projectId) {
        return getSqlSession().selectList(NAMESPACE.concat(".selectList"), projectId);
    }

}
