package org.focusns.console.impl;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */


import org.focusns.dao.console.UserDao;
import org.focusns.model.console.User;
import org.focusns.service.console.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User getUser(long userId) {
        return userDao.select(userId);
    }

    public void createUser(User user) {
        userDao.insert(user);
    }

    public void modifyUser(User user) {
        userDao.update(user);
    }

    public void removeUser(User user) {
        userDao.delete(user.getId());
    }

    public void assignRole(long userId, long roleId) {
        userDao.assign(userId, roleId);
    }

    public void unassignRole(long userId, long roleId) {
        userDao.unassign(userId, roleId);
    }

}
