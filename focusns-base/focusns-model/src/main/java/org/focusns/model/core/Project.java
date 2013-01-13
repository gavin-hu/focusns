/*
 * Copyright (C) 2011-2013 FocusSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.focusns.model.core;

import org.focusns.model.common.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project extends Id {
    
	private String code;
	private String title;
	private String description;
	private Date createAt;
	private Date modifyAt;
	private boolean _private;
	private long createById;
	private long modifyById;
	private long categoryId;
	private long logoId;
	//
	private User createBy;
	private User modifyBy;
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

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getModifyAt() {
		return modifyAt;
	}

	public void setModifyAt(Date modifyAt) {
		this.modifyAt = modifyAt;
	}
	
	public long getLogoId() {
		return logoId;
	}
	
	public void setLogoId(long logoId) {
		this.logoId = logoId;
	}

	public long getCreateById() {
		return createById;
	}

	public void setCreateById(long createById) {
		this.createById = createById;
	}

	public long getModifyById() {
		return modifyById;
	}

	public void setModifyById(long modifyById) {
		this.modifyById = modifyById;
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
	
	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	public User getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(User modifyBy) {
		this.modifyBy = modifyBy;
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
