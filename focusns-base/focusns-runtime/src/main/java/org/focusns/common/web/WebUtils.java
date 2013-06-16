package org.focusns.common.web;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class WebUtils {

    public static Map<String, String> getParameterMap(HttpServletRequest request) {
        //
        Map<String, String> parameterMap = new LinkedHashMap<String, String>();
        //
        String requestUri = request.getRequestURI();
        if(requestUri.contains(";")) {
            String paramsString = requestUri.substring(requestUri.indexOf(";")+1);
            String[] paramsPair = new String[]{paramsString};
            if(paramsString.contains(",")) {
                paramsPair = StringUtils.split(paramsString, ",");
            }
            for(String paramPair : paramsPair) {
                String[] nameAndValue = StringUtils.split(paramPair, "=");
                parameterMap.put(nameAndValue[0], nameAndValue[1]);
            }
        }
        return parameterMap;
    }

}
