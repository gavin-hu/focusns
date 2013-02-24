package org.focusns.console.impl;

import org.focusns.dao.console.AuthorityDao;
import org.focusns.model.console.Authority;
import org.focusns.service.console.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityDao authorityDao;

    public Authority getAuthority(long authorityId) {
        return authorityDao.select(authorityId);
    }

    public void createAuthority(Authority authority) {
        authorityDao.insert(authority);
    }

    public void modifyAuthority(Authority authority) {
        authorityDao.update(authority);
    }

    public void removeAuthority(Authority authority) {
        authorityDao.delete(authority.getId());
    }
}
