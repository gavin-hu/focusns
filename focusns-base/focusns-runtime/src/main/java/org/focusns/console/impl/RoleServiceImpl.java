

package org.focusns.console.impl;

import org.focusns.dao.console.RoleDao;
import org.focusns.model.console.Role;
import org.focusns.service.console.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role getRole(long roleId) {
        return roleDao.select(roleId);
    }

    @Override
    public void createRole(Role role) {
        roleDao.insert(role);
    }

    @Override
    public void modifyRole(Role role) {
        roleDao.update(role);
    }

    @Override
    public void removeRole(Role role) {
        roleDao.delete(role.getId());
    }

    @Override
    public void authorizeAuthority(long roleId, long authorityId) {
        roleDao.authorize(roleId, authorityId);
    }

    @Override
    public void unauthorizeAuthority(long roleId, long authorityId) {
        roleDao.unauthorize(roleId, authorityId);
    }
}
