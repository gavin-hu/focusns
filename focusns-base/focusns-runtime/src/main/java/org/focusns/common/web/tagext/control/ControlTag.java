package org.focusns.common.web.tagext.control;

import org.focusns.common.web.tagext.GenericTag;
import org.focusns.common.web.tagext.control.view.ClassPathViewNameResolver;
import org.focusns.common.web.tagext.control.view.FreeMarkerViewRender;
import org.focusns.common.web.tagext.control.view.ViewNameResolver;
import org.focusns.common.web.tagext.control.view.ViewRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.beans.PropertyDescriptor;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public abstract class ControlTag extends GenericTag {

    private String styleId;
    private String styleClass;
    //
    protected ViewRender controlViewRender;
    protected ViewNameResolver controlViewNameResolver;
    //
    public String getStyleId() {
        return styleId!=null?styleId:"";
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public String getStyleClass() {
        return styleClass!=null?styleClass:"";
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    @Autowired
    @Qualifier("controlViewRender")
    public void setControlViewRender(ViewRender controlViewRender) {
        this.controlViewRender = controlViewRender;
    }

    @Autowired
    @Qualifier("controlViewNameResolver")
    public void setControlViewNameResolver(ViewNameResolver controlViewNameResolver) {
        this.controlViewNameResolver = controlViewNameResolver;
    }

    @Override
    public int doStartTag() throws JspException {
        //
        Map<String, Object> model = createAndPrepareModel();
        //
        renderComponent(model);
        //
        return SKIP_BODY;
    }

    protected void renderComponent(Map<String, Object> model) throws JspException {
        try {
            //
            String componentViewName = controlViewNameResolver.resolveViewName(getClass(), this);
            String componentViewResult = controlViewRender.renderView(componentViewName, model);
            pageContext.getOut().append(componentViewResult).flush();
        } catch (Exception e) {
            throw new JspException(e.getMessage(), e);
        }
    }

    protected void exposeAttributesToModel(Map<String,Object> model) {
        //  expose attributes
        for(String attributeName : exposeAttributeNames()) {
            if(pageContext.getAttribute(attributeName)!=null) {
                model.put(attributeName, pageContext.getAttribute(attributeName));
            } else {
                throw new NullPointerException(
                        String.format("Expose attribute %s is not in request!", attributeName));
            }
        }
    }


    protected Map<String, Object> createAndPrepareModel() {
        Map<String, Object> model = new HashMap<String, Object>();
        //
        for(PropertyDescriptor pd : beanWrapper.getPropertyDescriptors()) {
            if(pd.getReadMethod()!=null) {
                //
                String name = pd.getName();
                Object value = beanWrapper.getPropertyValue(name);
                model.put(name, value);
            }
        }
        //
        prepareModel(model);
        //
        Enumeration<String> e = pageContext.getAttributeNamesInScope(PageContext.PAGE_SCOPE);
        while(e.hasMoreElements()) {
            String name = e.nextElement();
            Object value = pageContext.getAttribute(name);
            model.put(name, value);
        }
        //
        return model;
    }

    protected String[] exposeAttributeNames() {
        return new String[0];
    }

    protected abstract void prepareModel(Map<String, Object> model);

}
