package org.focusns.model.core;

/*
 * #%L
 * FocusSNS Model
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.focusns.model.common.Id;

public class Project extends Id {

    public static String KEY = Project.class.getName();

    private String code;
    private String title;
    private String description;
    private Date createdAt;
    private Date modifiedAt;
    private boolean _private;
    private long createdById;
    private long modifiedById;
    private long categoryId;
    private long logoId;
    //
    private ProjectUser createdBy;
    private ProjectUser modifiedBy;
    private ProjectLogo logo;
    private ProjectCategory category;
    private List<ProjectAttribute> attributes = new ArrayList<ProjectAttribute>();
    private List<ProjectFeature> features = new ArrayList<ProjectFeature>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public long getLogoId() {
        return logoId;
    }

    public void setLogoId(long logoId) {
        this.logoId = logoId;
    }

    public long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(long createdById) {
        this.createdById = createdById;
    }

    public long getModifiedById() {
        return modifiedById;
    }

    public void setModifiedById(long modifiedById) {
        this.modifiedById = modifiedById;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isPrivate() {
        return _private;
    }

    public void setPrivate(boolean _private) {
        this._private = _private;
    }

    public ProjectUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(ProjectUser createdBy) {
        this.createdBy = createdBy;
    }

    public ProjectUser getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(ProjectUser modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public ProjectLogo getLogo() {
        return logo;
    }

    public void setLogo(ProjectLogo logo) {
        this.logo = logo;
    }

    public ProjectCategory getCategory() {
        return category;
    }

    public void setCategory(ProjectCategory category) {
        this.category = category;
    }

    public List<ProjectAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ProjectAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<ProjectFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<ProjectFeature> features) {
        this.features = features;
    }

}
