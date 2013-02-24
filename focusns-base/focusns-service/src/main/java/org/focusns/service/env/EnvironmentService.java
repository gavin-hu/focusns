package org.focusns.service.env;

import org.focusns.model.env.Environment;

public interface EnvironmentService {

	Environment lookupEnvironment(Environment.Type type);
	
}
