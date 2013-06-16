package org.focusns.common.web.page.config;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PageConfigKey {

    private static final List<String> PARAM_NAMES_EXCLUDE = Arrays.asList(new String[]{
            "path", "layout"
    });

    public static String generateKey(PageConfig pageConfig) {
        return generateKey(pageConfig.getPath(), pageConfig.getParameters());
    }

    public static String generateKey(String path, Map<String, ?> paramsMap) {
        if (paramsMap.isEmpty()) {
            return path;
        }
        //
        StringBuilder sb = new StringBuilder(path);
        for(String paramName : paramsMap.keySet()) {
            if(PARAM_NAMES_EXCLUDE.contains(paramName)) {
                continue;
            }
            //
            String paramValue = String.valueOf(paramsMap.get(paramName));
            if (StringUtils.hasText(paramValue)) {
                sb.append(paramName).append("=").append(paramsMap.get(paramName));
            }
        }
        //
        return sb.toString();
    }

}
