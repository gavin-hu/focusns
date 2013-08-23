package org.focusns.model.core;

import org.focusns.model.common.Id;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectTemplate extends Id {

    private String description;
    //
    private List<ProjectFeature> projectFeatures = new ArrayList<ProjectFeature>();
    private List<ProjectAttribute> projectAttributes = new ArrayList<ProjectAttribute>();

    public ProjectTemplate() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProjectFeature> getProjectFeatures() {
        return Collections.unmodifiableList(projectFeatures);
    }

    public List<ProjectAttribute> getProjectAttributes() {
        return Collections.unmodifiableList(projectAttributes);
    }

    public void addProjectFeature(ProjectFeature projectFeature) {
        this.projectFeatures.add(projectFeature);
    }

    public void addProjectAttribute(ProjectAttribute projectAttribute) {
        this.projectAttributes.add(projectAttribute);
    }

}
