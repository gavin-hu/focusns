

package org.focusns.service.console;

import org.focusns.model.console.Authority;

public interface AuthorityService {

    Authority getAuthority(long authorityId);

    void createAuthority(Authority authority);

    void modifyAuthority(Authority authority);

    void removeAuthority(Authority authority);
}
