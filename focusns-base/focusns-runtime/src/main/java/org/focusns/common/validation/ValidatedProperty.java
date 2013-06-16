package org.focusns.common.validation;

/*
 * #%L
 * FocusSNS Runtime
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
