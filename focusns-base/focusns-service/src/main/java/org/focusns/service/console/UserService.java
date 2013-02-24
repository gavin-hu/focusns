package org.focusns.service.console;

import org.focusns.model.console.User;

public interface UserService {

    User getUser(long userId);

    void createUser(User user);

    void modifyUser(User user);

    void removeUser(User user);

    void assignRole(long userId, long roleId);

    void unassignRole(long userId, long roleId);

}
