package org.focusns.common.web.tagext.control;

import javax.servlet.jsp.JspException;
import java.util.Map;

public abstract class ContainerControlTag extends ControlTag {

    private Map<String, Object> model;

    @Override
    public int doStartTag() throws JspException {
        //
        this.model = createAndPrepareModel();
        this.model.put("renderMode", "doStartTag");
        renderComponent(model);
        //
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspException {
        //
        this.model.put("renderMode", "doEndTag");
        renderComponent(model);
        //
        return super.doEndTag();
    }
}
