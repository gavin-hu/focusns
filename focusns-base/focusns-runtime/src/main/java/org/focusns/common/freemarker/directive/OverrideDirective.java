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
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class OverrideDirective implements TemplateDirectiveModel {

    public final static String DIRECTIVE_NAME = "override";

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        String name = DirectiveUtils.getRequiredParam(params, "name");
        String overrideVariableName = DirectiveUtils.getOverrideVariableName(name);

        TemplateDirectiveBodyOverrideWraper override = DirectiveUtils.getOverrideBody(env, name);
        TemplateDirectiveBodyOverrideWraper current = new TemplateDirectiveBodyOverrideWraper(body, env);
        if (override == null) {
            env.setVariable(overrideVariableName, current);
        } else {
            DirectiveUtils.setTopBodyForParentBody(env, current, override);
        }
    }

    static class TemplateDirectiveBodyOverrideWraper implements TemplateDirectiveBody, TemplateModel {
        private TemplateDirectiveBody body;
        public TemplateDirectiveBodyOverrideWraper parentBody;
        public Environment env;

        public TemplateDirectiveBodyOverrideWraper(TemplateDirectiveBody body, Environment env) {
            super();
            this.body = body;
            this.env = env;
        }

        public void render(Writer out) throws TemplateException, IOException {
            if (body == null) {
                return;
            }
            TemplateDirectiveBodyOverrideWraper preOverridy = (TemplateDirectiveBodyOverrideWraper) env.getVariable(DirectiveUtils.OVERRIDE_CURRENT_NODE);
            try {
                env.setVariable(DirectiveUtils.OVERRIDE_CURRENT_NODE, this);
                body.render(out);
            } finally {
                env.setVariable(DirectiveUtils.OVERRIDE_CURRENT_NODE, preOverridy);
            }
        }
    }

}
