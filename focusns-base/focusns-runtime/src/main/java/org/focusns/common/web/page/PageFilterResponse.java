package org.focusns.common.web.page;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class PageFilterResponse extends HttpServletResponseWrapper {

    public PageFilterResponse(HttpServletResponse response) {
        super(response);
    }

}
