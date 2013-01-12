package org.focusns.service.env;

import org.focusns.model.env.Environment;
import org.focusns.model.env.Environment.Type;
import org.focusns.service.AbstractServiceTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class EnvironmentServiceTest extends AbstractServiceTest{

	@Autowired
	private EnvironmentService environmentService;
	
	@Test
	public void testOSEnv() {
		Environment envOS = environmentService.lookupEnvironment(Type.OS);
		System.out.println(envOS);
	}
	
	@Test
	public void testJavaEnv() {
		Environment jvmOS = environmentService.lookupEnvironment(Type.JAVA);
		System.out.println(jvmOS);
	}
	
	@Test
	public void testDBEnv() {
		Environment envOS = environmentService.lookupEnvironment(Type.DB);
		System.out.println(envOS);
	}
	
}
