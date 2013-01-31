
package org.focusns.common.monitor.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PerformanceMonitorFilter extends OncePerRequestFilter {

    private static final Log log = LogFactory.getLog(PerformanceMonitorFilter.class);
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
            FilterChain filterChain) throws ServletException, IOException {
        //
        if(log.isTraceEnabled()) {
            //
            String requestDesc = getRequestDescription(request);
            StopWatch stopWatch = new StopWatch();
            stopWatch.start(requestDesc);
            //
            filterChain.doFilter(request, response);
            //
            stopWatch.stop();
            //
            log.trace(stopWatch.prettyPrint());
        } else {
            filterChain.doFilter(request, response);
        }
    }
    
    private String getRequestDescription(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder(request.getRequestURI());
        if(StringUtils.hasText(request.getQueryString())) {
            sb.append("?").append(request.getQueryString());
        }
        sb.append(" [").append(request.getMethod()).append("]");
        return sb.toString();
    }
    
}
