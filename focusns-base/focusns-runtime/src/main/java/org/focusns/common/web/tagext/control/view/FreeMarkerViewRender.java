package org.focusns.common.web.tagext.control.view;

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

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

public class FreeMarkerViewRender implements ViewRender, InitializingBean {

    private String encoding = "UTF-8";
    private TemplateLoader templateLoader;
    private Class classForTemplateLoader = getClass();
    //
    private Configuration configuration;

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setTemplateLoader(TemplateLoader templateLoader) {
        this.templateLoader = templateLoader;
    }

    public void setClassForTemplateLoader(Class classForTemplateLoader) {
        this.classForTemplateLoader = classForTemplateLoader;
    }

    public void afterPropertiesSet() throws Exception {
        //
        configuration = new Configuration();
        configuration.setEncoding(Locale.getDefault(), encoding);
        //
        if (templateLoader == null) {
            configuration.setClassForTemplateLoading(classForTemplateLoader, "/");
        } else {
            configuration.setTemplateLoader(templateLoader);
        }
        //
    }

    public String renderView(String viewName, Map<String, Object> model) {
        //
        try {
            Template template = configuration.getTemplate(viewName + ".ftl");
            StringWriter StringWriter = new StringWriter();
            template.process(model, StringWriter);
            return StringWriter.toString();
        } catch (Exception e) {
            throw new ViewRenderException(e.getMessage(), e);
        }
    }

}
