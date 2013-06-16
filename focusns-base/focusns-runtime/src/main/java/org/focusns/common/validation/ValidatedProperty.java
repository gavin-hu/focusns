package org.focusns.common.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidatedProperty {

	private String name;

	private List<ValidatedConstraint> validatedConstraints = new ArrayList<ValidatedConstraint>();

	public ValidatedProperty(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ValidatedConstraint> getValidatedConstraints() {
		return validatedConstraints;
	}
	
	public void addValidatedConstraint(ValidatedConstraint validateConstraint) {
		this.validatedConstraints.add(validateConstraint);
	}

	public void setValidatedConstraints(List<ValidatedConstraint> validatedConstraints) {
		this.validatedConstraints = validatedConstraints;
	}

}
