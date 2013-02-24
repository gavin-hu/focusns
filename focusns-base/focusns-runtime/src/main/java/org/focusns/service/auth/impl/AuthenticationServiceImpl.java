package org.focusns.service.auth.impl;

import org.focusns.dao.core.ProjectUserDao;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.auth.AuthenticationException;
import org.focusns.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private ProjectUserDao projectUserDao;
    
    public void authenticate(ProjectUser user) {
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        ProjectUser dbUser = projectUserDao.selectByUsername(user.getUsername());
        //
        if(!md5Password.equals(dbUser.getPassword())) {
            throw new AuthenticationException("Username or password miss matched!");
        }
    }
    
}
