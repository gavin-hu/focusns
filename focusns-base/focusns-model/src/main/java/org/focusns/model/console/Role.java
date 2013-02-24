package org.focusns.model.console;

import org.focusns.model.common.Id;

public class Role extends Id {
    //
    private String label;
    private int level;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
