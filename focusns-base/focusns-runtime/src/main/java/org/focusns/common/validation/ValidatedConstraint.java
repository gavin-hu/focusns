package org.focusns.common.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidatedConstraint {

	private String name;
	private List<String> params = new ArrayList<String>();
	//
	private String message;

	public ValidatedConstraint(String name, List<String> params) {
		this.name = name;
        this.params = params;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public List<String> getParams() {
        return params;
    }

    public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
