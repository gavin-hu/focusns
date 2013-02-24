package org.focusns.model.env;

public class EnvironmentJava extends Environment {

	private String javaHome;
	private String javaOptions;
	private String javaVendor;
	private String javaVersion;
	private String javaRuntimeName;
	private String javaRuntimeVersion;
	private String javaClassPath;
	private String javaLibraryPath;
	//
	private String javaVMName;
	private String javaVMVendor;
	private String javaVMVersion;
	private String javaVMInfo;
	
	public EnvironmentJava() {
	}

	public String getJavaHome() {
		return javaHome;
	}

	public void setJavaHome(String javaHome) {
		this.javaHome = javaHome;
	}

	public String getJavaOptions() {
		return javaOptions;
	}

	public void setJavaOptions(String javaOptions) {
		this.javaOptions = javaOptions;
	}

	public String getJavaVendor() {
		return javaVendor;
	}

	public void setJavaVendor(String javaVendor) {
		this.javaVendor = javaVendor;
	}

	public String getJavaVersion() {
		return javaVersion;
	}

	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

	public String getJavaRuntimeName() {
		return javaRuntimeName;
	}
	
	public void setJavaRuntimeName(String javaRuntimeName) {
		this.javaRuntimeName = javaRuntimeName;
	}
	
	public String getJavaRuntimeVersion() {
		return javaRuntimeVersion;
	}
	
	public void setJavaRuntimeVersion(String javaRuntimeVersion) {
		this.javaRuntimeVersion = javaRuntimeVersion;
	}

	public String getJavaClassPath() {
		return javaClassPath;
	}
	
	public void setJavaClassPath(String javaClassPath) {
		this.javaClassPath = javaClassPath;
	}
	
	public String getJavaLibraryPath() {
		return javaLibraryPath;
	}
	
	public void setJavaLibraryPath(String javaLibraryPath) {
		this.javaLibraryPath = javaLibraryPath;
	}

	public String getJavaVMName() {
		return javaVMName;
	}

	public void setJavaVMName(String javaVMName) {
		this.javaVMName = javaVMName;
	}

	public String getJavaVMVendor() {
		return javaVMVendor;
	}

	public void setJavaVMVendor(String javaVMVendor) {
		this.javaVMVendor = javaVMVendor;
	}

	public String getJavaVMVersion() {
		return javaVMVersion;
	}

	public void setJavaVMVersion(String javaVMVersion) {
		this.javaVMVersion = javaVMVersion;
	}

	public String getJavaVMInfo() {
		return javaVMInfo;
	}

	public void setJavaVMInfo(String javaVMInfo) {
		this.javaVMInfo = javaVMInfo;
	}
	
}
