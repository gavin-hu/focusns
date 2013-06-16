package org.focusns.common.validation;

import java.util.ArrayList;
import java.util.List;


public class ValidatedBean {

	private String beanName;
	private Class<?> beanType;
	
	private List<ValidatedProperty> validatedProperties = new ArrayList<ValidatedProperty>();

	public ValidatedBean(String beanName, Class<?> beanType) {
		this.beanName = beanName;
		this.beanType = beanType;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	
	public String getBeanClassName() {
		return this.beanType.getName();
	}
	
	public String getBeanClassSimpleName() {
		return this.beanType.getSimpleName();
	}
 
	public Class<?> getBeanType() {
		return beanType;
	}

	public void setBeanType(Class<?> beanType) {
		this.beanType = beanType;
	}
	
	public List<ValidatedProperty> getValidatedProperties() {
		return validatedProperties;
	}
	
	public void addValidatedProperty(ValidatedProperty validatedProperty) {
		this.validatedProperties.add(validatedProperty);
	}
	
	public void setValidatedProperties(List<ValidatedProperty> validatedProperties) {
		this.validatedProperties = validatedProperties;
	}
	
}
