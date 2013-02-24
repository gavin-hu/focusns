package org.focusns.service.auth;

import org.focusns.model.core.ProjectUser;

public interface AuthenticationService {
    
    void authenticate(ProjectUser user);
    
}
