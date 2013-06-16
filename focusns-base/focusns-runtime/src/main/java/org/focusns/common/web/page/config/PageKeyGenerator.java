package org.focusns.common.web.page.config;

import java.util.Map;

public interface PageKeyGenerator {

    String generate(String name, Map<String, String> params);

}
