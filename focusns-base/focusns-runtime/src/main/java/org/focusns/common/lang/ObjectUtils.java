package org.focusns.common.lang;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;

public class ObjectUtils extends org.springframework.util.ObjectUtils {

    public static void extend(Object object1, Object object2) {
        //
        Assert.isAssignable(object2.getClass(), object1.getClass());
        //
        BeanWrapper object1Wrapper = PropertyAccessorFactory.forBeanPropertyAccess(object1);
        BeanWrapper object2Wrapper = PropertyAccessorFactory.forBeanPropertyAccess(object2);
        //
        for(PropertyDescriptor pd : object2Wrapper.getPropertyDescriptors()) {
            if(pd.getPropertyType().isPrimitive()) {
                continue;
            }
            //
            Object value = object2Wrapper.getPropertyValue(pd.getName());
            if(value!=null && object1Wrapper.isWritableProperty(pd.getName())) {
                object1Wrapper.setPropertyValue(pd.getName(), value);
            }
        }
    }
}
