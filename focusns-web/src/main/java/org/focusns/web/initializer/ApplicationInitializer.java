package org.focusns.web.initializer;

import org.focusns.web.helper.ApplicationHelper;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class ApplicationInitializer  implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	public void initialize(ConfigurableApplicationContext applicationContext) {
        // check runtime environment
        checkRuntimeInstalled();
	}

    private void checkRuntimeInstalled() {
        if(!ApplicationHelper.getInstance().isInstalled()) {
            throw new RuntimeException("FocusSNS Runtime is not installed now!");
        }
    }
}
