/*
 * Copyright (C) 2012 FocuSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.focusns.service.core.impl;

import org.focusns.dao.core.ProjectDao;
import org.focusns.dao.core.UserDao;
import org.focusns.model.core.Project;
import org.focusns.model.core.User;
import org.focusns.service.core.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
    @Autowired
    private ProjectDao projectDao;
	
    public User getUser(String username) {
        User user = userDao.selectByUsername(username);
        if(user.getProjectId()>0) {
            Project project = projectDao.select(user.getProjectId());
            user.setProject(project);
        }
        //
        return user;
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

}
