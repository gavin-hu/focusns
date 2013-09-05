package org.focusns.model.core;

import org.focusns.model.common.Id;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProjectTemplate extends Id {

    private String description;
    //
    private List<ProjectFeature> projectFeatures = new ArrayList<ProjectFeature>();
    private List<ProjectAttribute> projectAttributes = new ArrayList<ProjectAttribute>();
    //
    private Map<ProjectRole, List<ProjectAuthority>> projectRoleAuthorityMap = new LinkedHashMap<ProjectRole, List<ProjectAuthority>>();

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

    public List<ProjectRole> getProjectRoles() {
        return Collections.unmodifiableList(new ArrayList<ProjectRole>(projectRoleAuthorityMap.keySet()));
    }

    public List<ProjectAuthority> getProjectAuthorities(ProjectRole projectRole) {
        List<ProjectAuthority> projectAuthorities = projectRoleAuthorityMap.get(projectRole);
        return Collections.unmodifiableList(projectAuthorities);
    }

    public void addProjectFeature(ProjectFeature projectFeature) {
        this.projectFeatures.add(projectFeature);
    }

    public void addProjectAttribute(ProjectAttribute projectAttribute) {
        this.projectAttributes.add(projectAttribute);
    }

    public void addProjectRoleAuthorities(ProjectRole projectRole, List<ProjectAuthority> projectAuthorities) {
        this.projectRoleAuthorityMap.put(projectRole, projectAuthorities);
    }

}
