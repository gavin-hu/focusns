package org.focusns.common.freemarker.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;

import freemarker.ext.beans.BeanModel;
import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateModelException;

public class DynamicInvoker {

    private SimpleHash simpleHash;

    private Map<String, BeanWrapper> beanWrapperMap = new HashMap<String, BeanWrapper>();

    public DynamicInvoker(SimpleHash simpleHash) {
        this.simpleHash = simpleHash;
    }

    public void create(String name) {
        String key = generateKey(name);
        Object target = findTarget(name);
        // target is null
        if(target==null) {
            return ;
        }
        // check type
        Assert.isAssignable(BeanModel.class, target.getClass());
        target = ((BeanModel)target).getWrappedObject();
        // new BeanWrapper
        BeanWrapper beanWrapper = new BeanWrapperImpl(target);
        beanWrapper.setAutoGrowNestedPaths(true);
        beanWrapperMap.put(key, beanWrapper);
    }

    public Object invoke(String path) {
        String name = path.substring(0, path.indexOf("."));
        String propertyPath = path.substring(path.indexOf(".") + 1);
        String key = generateKey(name);
        BeanWrapper beanWrapper = beanWrapperMap.get(key);
        // check beanWrapper
        if(beanWrapper==null) {
            return null;
        }
        //
        return beanWrapper.getPropertyValue(propertyPath);
    }

    private String generateKey(String name) {
        return name.concat(Thread.currentThread().getName());
    }

    private Object findTarget(String name) {
        Object target = null;
        try {
            SimpleHash model = (SimpleHash) simpleHash.get(FreemarkerServlet.KEY_REQUEST);
            target = model.get(name);
            //
            if(target==null) {
                model = (SimpleHash) simpleHash.get(FreemarkerServlet.KEY_SESSION);
                target = model.get(name);
            }
            //
            if(target==null) {
                model = (SimpleHash) simpleHash.get(FreemarkerServlet.KEY_APPLICATION);
                target = model.get(name);
            }
        } catch (TemplateModelException e) {
            e.printStackTrace();
        }
        //
        return target;
    }

}
