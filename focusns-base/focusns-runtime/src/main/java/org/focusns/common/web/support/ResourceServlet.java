package org.focusns.common.web.support;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.support.ServletContextResourceLoader;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ResourceServlet extends HttpServletBean {

    private UrlPathHelper urlPathHelper = new UrlPathHelper();
    private ResourceLoader resourceLoader;
    //
    @Override
    protected void initServletBean() throws ServletException {
        //
        this.resourceLoader = new ServletContextResourceLoader(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
        String lookupPath = urlPathHelper.getLookupPathForRequest(req);
        Resource resource = resourceLoader.getResource("/WEB-INF" + lookupPath);

        //
        if(resource.exists()) {
            FileCopyUtils.copy(resource.getInputStream(), resp.getOutputStream());
        } else {
            throw new FileNotFoundException(lookupPath);
        }
    }
}
