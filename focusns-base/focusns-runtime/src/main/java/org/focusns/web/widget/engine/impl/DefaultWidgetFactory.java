
package org.focusns.web.widget.engine.impl;

import org.focusns.web.widget.engine.WidgetFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class DefaultWidgetFactory implements WidgetFactory, BeanFactoryAware {

	private BeanFactory beanFactory;
	
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
	
	public Object getWidget(String className) throws Exception {
		//
		return beanFactory.getBean(Class.forName(className));
	}

}
