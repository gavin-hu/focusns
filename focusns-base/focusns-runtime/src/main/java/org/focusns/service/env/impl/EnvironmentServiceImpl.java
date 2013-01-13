/*
 * Copyright (C) 2011-2013 FocusSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.focusns.service.env.impl;

import org.focusns.model.env.Environment;
import org.focusns.model.env.Environment.Type;
import org.focusns.model.env.EnvironmentDB;
import org.focusns.model.env.EnvironmentJava;
import org.focusns.model.env.EnvironmentOS;
import org.focusns.service.env.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {
	
	@Autowired
	private DataSource dataSource;
	
	private EnvironmentOS envOS;
	private EnvironmentJava envJava;

	public Environment lookupEnvironment(Type type) {
		if(Type.OS==type) {
			return lookupOS();
		} else if(Type.JAVA==type) {
			return lookupJava();
		} else if(Type.DB==type) {
			return lookupDB();
		}
		//
		throw new IllegalArgumentException(type.toString());
	}
	
	protected Environment lookupOS() {
		if(envOS==null) {
			Properties props = System.getProperties();
			Map<String, String> env = System.getenv();
			//
			envOS = new EnvironmentOS();
			envOS.setOsName(props.getProperty("os.name"));
			envOS.setOsVersion(props.getProperty("os.version"));
			envOS.setOsArch(props.getProperty("os.arch"));
			envOS.setOsPatch(props.getProperty("sun.os.patch.level"));
			envOS.setOsPath(env.get("Path"));
		}
		//
		return envOS;
	}

	protected Environment lookupJava() {
		if(envJava==null) {
			Properties props = System.getProperties();
			Map<String, String> env = System.getenv();
			//
			envJava = new EnvironmentJava();
			envJava.setJavaVendor(props.getProperty("java.vendor"));
			envJava.setJavaVersion(props.getProperty("java.version"));
			envJava.setJavaHome(env.get("JAVA_HOME"));
			envJava.setJavaOptions(env.get("JAVA_OPTS"));
			envJava.setJavaClassPath(props.getProperty("java.class.path"));
			envJava.setJavaLibraryPath(props.getProperty("java.library.path"));
			envJava.setJavaRuntimeName(props.getProperty("java.runtime.name"));
			envJava.setJavaRuntimeVersion(props.getProperty("java.runtime.version"));
			//
			envJava.setJavaVMName(props.getProperty("java.vm.name"));
			envJava.setJavaVMVendor(props.getProperty("java.vm.vendor"));
			envJava.setJavaVMVersion(props.getProperty("java.vm.version"));
			envJava.setJavaVMInfo(props.getProperty("java.vm.info"));
		}
		//
		return envJava;
	}

	protected Environment lookupDB() {
		try {
			DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
			//
			EnvironmentDB envDB = new EnvironmentDB();
			envDB.setDatabaseName(metaData.getDatabaseProductName());
			envDB.setDatabaseVersion(metaData.getDatabaseProductVersion());
			envDB.setDriverName(metaData.getDriverName());
			envDB.setDriverVersion(metaData.getDriverVersion());
			envDB.setUrl(metaData.getURL());
			envDB.setUsername(metaData.getUserName());
			envDB.setMaxConnections(metaData.getMaxConnections());
			//
			metaData.getConnection().close();
			//
			return envDB;
		} catch (SQLException e) {
			throw new UnsupportedOperationException(e);
		}
	}
}
