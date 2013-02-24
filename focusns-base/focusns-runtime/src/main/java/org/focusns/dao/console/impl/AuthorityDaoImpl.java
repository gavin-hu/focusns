package org.focusns.dao.console.impl;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.console.AuthorityDao;
import org.focusns.model.console.Authority;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDaoImpl extends MyBatisBaseDao<Authority>
        implements AuthorityDao {
}
