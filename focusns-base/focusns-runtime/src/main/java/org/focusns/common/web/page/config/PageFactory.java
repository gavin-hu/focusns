package org.focusns.common.web.page.config;

import java.util.Map;

public interface PageFactory {

    PageConfig find(String name, Map<String, String> params) throws PageConfigException;

}
