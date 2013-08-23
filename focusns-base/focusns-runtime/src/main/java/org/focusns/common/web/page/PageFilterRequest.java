package org.focusns.common.web.page;

import org.focusns.common.web.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PageFilterRequest extends HttpServletRequestWrapper {

    private Map<String, String> parameterMap = new HashMap<String, String>();

    public PageFilterRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        if(super.getParameterMap().containsKey(name)) {
            return super.getParameter(name);
        }
        return this.parameterMap.get(name);
    }

    @Override
    public Map getParameterMap() {
        Map parameterMap = new HashMap();
        parameterMap.putAll(this.parameterMap);
        parameterMap.putAll(super.getParameterMap());
        return Collections.unmodifiableMap(parameterMap);
    }

    @Override
    public Enumeration getParameterNames() {
        final Iterator paramNameIter = this.getParameterMap().keySet().iterator();
        return new Enumeration(){
            @Override
            public boolean hasMoreElements() {
                return paramNameIter.hasNext();
            }

            @Override
            public Object nextElement() {
                return paramNameIter.next();
            }
        };
    }

    @Override
    public String[] getParameterValues(String name) {
        if(super.getParameterMap().containsKey(name)) {
            return super.getParameterValues(name);
        }
        return new String[]{this.parameterMap.get(name)};
    }

    public void addParameter(String name, String value) {
        this.parameterMap.put(name, value);
    }

    public void addParameters(Map<String, String> parameters) {
        this.parameterMap.putAll(parameters);
    }

    public void parseMatrixParameters() {
        //
        Map<String, String> matrixParameters = WebUtils.getMatrixParameters(this);
        this.addParameters(matrixParameters);
    }

}
