package org.focusns.web.page.directive;

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class PositionDirective implements TemplateDirectiveModel {

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		//
		if(!params.containsKey("name")) {
			throw new IllegalArgumentException("Argument name can not be null!");
		}
		String name = params.get("name").toString();
		//
		TemplateModel positionTemplateModel = env.getDataModel().get(name);
        if(positionTemplateModel!=null) {
            //
            body.render(env.getOut());
        }
	}

}
