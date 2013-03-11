package org.focusns.common.freemarker.directive;

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


import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import org.focusns.common.freemarker.directive.OverrideDirective.TemplateDirectiveBodyOverrideWraper;

import java.util.Map;

public class DirectiveUtils {
	
	public static String BLOCK = "__ftl_override__";
	public static String OVERRIDE_CURRENT_NODE = "__ftl_override_current_node";
	
	public static String getOverrideVariableName(String name) {
		return BLOCK + name;
	}
	
	public static void exposeRapidMacros(Configuration conf) throws TemplateModelException {
		conf.setSharedVariable(BlockDirective.DIRECTIVE_NAME, new BlockDirective());
		conf.setSharedVariable(ExtendsDirective.DIRECTIVE_NAME, new ExtendsDirective());
		conf.setSharedVariable(OverrideDirective.DIRECTIVE_NAME, new OverrideDirective());
		conf.setSharedVariable(SuperDirective.DIRECTIVE_NAME, new SuperDirective());
	}
	
	static String getRequiredParam(Map params,String key) throws TemplateException {
		Object value = params.get(key);
		if(value == null || "".equals(value.toString().trim())) {
			throw new TemplateModelException("not found required parameter:"+key+" for directive");
		}
		return value.toString();
	}
	
	static String getParam(Map params,String key,String defaultValue) throws TemplateException {
		Object value = params.get(key);
		return value == null ? defaultValue : value.toString();
	}
	
	static TemplateDirectiveBodyOverrideWraper getOverrideBody(Environment env, String name) throws TemplateModelException {
		TemplateDirectiveBodyOverrideWraper value = (TemplateDirectiveBodyOverrideWraper)env.getVariable(DirectiveUtils.getOverrideVariableName(name));
		return value;
	}
	
	static void setTopBodyForParentBody(Environment env,
			TemplateDirectiveBodyOverrideWraper topBody,
			TemplateDirectiveBodyOverrideWraper overrideBody) {
		TemplateDirectiveBodyOverrideWraper parent = overrideBody;
		while(parent.parentBody != null) {
			parent = parent.parentBody;
		}
		parent.parentBody = topBody;
	}
}
