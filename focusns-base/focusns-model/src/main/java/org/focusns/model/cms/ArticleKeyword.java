package org.focusns.model.cms;

import org.focusns.model.common.Id;

public class ArticleKeyword extends Id {

    private String label;
    //
    private long categoryId;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
