

package org.focusns.service.console;

import org.focusns.model.console.Role;

public interface RoleService {

    Role getRole(long roleId);

    void createRole(Role role);

    void modifyRole(Role role);

    void removeRole(Role role);

    void authorizeAuthority(long roleId, long authorityId);

    void unauthorizeAuthority(long roleId, long authorityId);

}
