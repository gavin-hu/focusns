package org.focusns.model.env;

public class EnvironmentOS extends Environment {

	private String osName;
	private String osPatch;
	private String osArch;
	private String osVersion;
	private String osPath;

	public EnvironmentOS() {
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsPatch() {
		return osPatch;
	}
	
	public void setOsPatch(String osPatch) {
		this.osPatch = osPatch;
	}
	
	public String getOsArch() {
		return osArch;
	}

	public void setOsArch(String osArch) {
		this.osArch = osArch;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getOsPath() {
		return osPath;
	}

	public void setOsPath(String osPath) {
		this.osPath = osPath;
	}

}
