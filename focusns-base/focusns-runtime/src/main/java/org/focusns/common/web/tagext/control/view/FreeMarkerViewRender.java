package org.focusns.common.web.tagext.control.view;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.InitializingBean;

import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

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
        if(templateLoader==null) {
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
