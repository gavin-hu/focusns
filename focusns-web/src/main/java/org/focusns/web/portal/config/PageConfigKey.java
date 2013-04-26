package org.focusns.web.portal.config;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class PageConfigKey {

    private static final String[] PARAM_NAMES = new String[] { "mode", "category" };

    public static String generateKey(PageConfig pageConfig) {
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("mode", pageConfig.getMode());
        paramsMap.put("category", pageConfig.getCategory());
        return generateKey(pageConfig.getPath(), paramsMap);
    }

    public static String generateKey(String path, Map<String, String> paramsMap) {
        if (paramsMap.isEmpty()) {
            return path;
        }
        //
        StringBuilder sb = new StringBuilder(path);
        for (String paramName : PARAM_NAMES) {
            String paramValue = paramsMap.get(paramName);
            if (StringUtils.hasText(paramValue)) {
                sb.append(paramName).append("=").append(paramsMap.get(paramName));
            }
        }
        //
        return sb.toString();
    }

}
