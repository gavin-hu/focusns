
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
	private ProjectUser createBy;
	private ProjectUser modifyBy;
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
	
	public ProjectUser getCreateBy() {
		return createBy;
	}

	public void setCreateBy(ProjectUser createBy) {
		this.createBy = createBy;
	}

	public ProjectUser getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(ProjectUser modifyBy) {
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
