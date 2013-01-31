

package org.focusns.model.core;

import org.focusns.model.common.Id;

public class ProjectAuthority extends Id {

    private String code;
    private String description;
    //

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
