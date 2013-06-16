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
