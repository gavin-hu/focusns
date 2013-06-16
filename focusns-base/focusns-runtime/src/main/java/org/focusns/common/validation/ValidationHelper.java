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

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.MessageInterpolator;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.ConstraintDescriptor;
import javax.validation.metadata.PropertyDescriptor;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

public abstract class ValidationHelper {

    /**
     * 根据 bean class 及 分组 获取当前分组的 校验元数据
     * 
     * @param validatorFactory
     * @param clazz
     * @param groups
     * @return
     */
    public static ValidatedBean createForClass(ValidatorFactory validatorFactory, Class<?> clazz, Class<?>... groups) {
        Class<?> beanType = clazz;
        String beanName = StringUtils.uncapitalize(clazz.getSimpleName());
        //
        ValidatedBean validatedBean = new ValidatedBean(beanName, beanType);
        //
        Validator validator = validatorFactory.getValidator();
        MessageInterpolator messageInterpolator = validatorFactory.getMessageInterpolator();
        //
        BeanDescriptor beanDescriptor = validator.getConstraintsForClass(beanType);
        Set<PropertyDescriptor> propertyDescriptors = beanDescriptor.getConstrainedProperties();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getPropertyName();
            ValidatedProperty validatedProperty = new ValidatedProperty(propertyName);
            //
            Set<ConstraintDescriptor<?>> constraintDesctipors = propertyDescriptor.getConstraintDescriptors();
            for (final ConstraintDescriptor<?> constraintDescriptor : constraintDesctipors) {
                // groups checks
                if (isOutOfGroup(constraintDescriptor, groups)) {
                    continue;
                }
                //
                String constraintName = getConstraintName(constraintDescriptor);
                List<String> constraintParams = getConstraintParams(constraintDescriptor);
                ValidatedConstraint validatedConstraint = new ValidatedConstraint(constraintName, constraintParams);
                //
                String messageTemplate = (String) constraintDescriptor.getAttributes().get("message");
                MessageInterpolator.Context context = new MessageInterpolatorContext(constraintDescriptor);
                String message = messageInterpolator.interpolate(messageTemplate, context);
                //
                validatedConstraint.setMessage(message);
                //
                validatedProperty.addValidatedConstraint(validatedConstraint);
            }
            //
            if (!validatedProperty.getValidatedConstraints().isEmpty()) {
                //
                validatedBean.addValidatedProperty(validatedProperty);
            }
        }
        //
        return validatedBean;
    }

    /**
     * 判断 constraintDescriptor 是否在给定的分组范围之内
     * 
     * @param constraintDescriptor
     * @param groups
     * @return
     */
    private static boolean isOutOfGroup(ConstraintDescriptor<?> constraintDescriptor, Class<?>... groups) {
        //
        if (groups == null || groups.length == 0) {
            return false;
        }
        //
        boolean outOfFlag = true;
        for (Class<?> group : groups) {
            for (Class<?> target : constraintDescriptor.getGroups()) {
                if (ClassUtils.isAssignable(group, target)) {
                    outOfFlag = false;
                }
            }
        }
        return outOfFlag;
    }

    /**
     * 获取约束名称
     * 
     * @param constraintDescriptor
     * @return
     */
    private static String getConstraintName(ConstraintDescriptor<?> constraintDescriptor) {
        return constraintDescriptor.getAnnotation().annotationType().getSimpleName().toLowerCase();
    }

    /**
     * 获取约束
     * 
     * @param constraintDescriptor
     * @return
     */
    private static List<String> getConstraintParams(ConstraintDescriptor<?> constraintDescriptor) {
        Annotation constraintInstance = constraintDescriptor.getAnnotation();
        Class<?> constraintClass = constraintDescriptor.getAnnotation().annotationType();
        //
        List<String> params = new ArrayList<String>();
        //
        Method valueMethod = ClassUtils.getMethodIfAvailable(constraintClass, "value", (Class<?>[]) null);
        if (valueMethod != null) {
            String value = String.valueOf(ReflectionUtils.invokeMethod(valueMethod, constraintInstance));
            params.add("value:" + value);
        }
        Map<String, Object> annotationAttrs = AnnotationUtils.getAnnotationAttributes(constraintInstance);
        for (String key : annotationAttrs.keySet()) {
            if ("message".equals(key) || "payload".equals(key)) {
                continue;
            }
            //
            String value = "";
            if ("groups".equals(key)) {
                List<String> groupNames = new ArrayList<String>();
                Class[] groupClasses = (Class[]) annotationAttrs.get(key);
                for (Class groupClass : groupClasses) {
                    groupNames.add(groupClass.getName());
                }
                value = StringUtils.collectionToDelimitedString(groupNames, "|");
            } else {
                value = String.valueOf(annotationAttrs.get(key));
            }
            //
            if (StringUtils.hasText(value)) {
                params.add(key + ":" + "'" + value + "'");
            }
        }
        //
        return params;
    }

    private static class MessageInterpolatorContext implements MessageInterpolator.Context {

        private ConstraintDescriptor<?> constraintDescriptor;

        public MessageInterpolatorContext(ConstraintDescriptor<?> constraintDescriptor) {
            this.constraintDescriptor = constraintDescriptor;
        }

        public ConstraintDescriptor<?> getConstraintDescriptor() {
            return constraintDescriptor;
        }

        public Object getValidatedValue() {
            return null;
        }

    }

}
