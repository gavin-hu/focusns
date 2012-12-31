/*
 * Copyright (C) 2012 FocusSNS.
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
package org.focusns.runtime;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.focusns.common.image.ImageUtils;
import org.focusns.model.core.Project;
import org.focusns.model.setup.DbConfig;
import org.focusns.model.setup.SiteConfig;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.NumberUtils;
import org.springframework.util.PropertiesPersister;

public class RuntimeHelper {

    private static final String TMP_DIR = System.getProperty("java.io.tmpdir");
    
    private static final String RUNTIME_DIR = System.getProperty("user.home") + File.separator + ".focusns";

    private static final File application = new File(RUNTIME_DIR, "application.properties");
    
    private static RuntimeHelper instance = new RuntimeHelper();
    
    private PropertiesPersister propsPersister = new DefaultPropertiesPersister();

    private RuntimeHelper() {
    }
    
    public static RuntimeHelper getInstance() {
        return instance;
    }
    
    public boolean isInstalled() {
        return application.exists();
    }
    
    public void install(SiteConfig siteConfig, DbConfig dbConfig) throws IOException {
        Properties props = new Properties();
        props.setProperty("site.title", siteConfig.getTitle());
        props.setProperty("site.keywords", siteConfig.getKeywords());
        props.setProperty("site.description", siteConfig.getDescription());
        //
        if("mysql".equals(dbConfig.getType())) {
            props.setProperty("jdbc.driver", "com.mysql.jdbc.Driver");
            props.setProperty("jdbc.url", String.format("jdbc:mysql://%s:%s/%s", 
                    dbConfig.getHost(), dbConfig.getPort(), dbConfig.getSchema()));
        }
        props.setProperty("jdbc.username", dbConfig.getUsername());
        props.setProperty("jdbc.password", dbConfig.getPassword());
        props.setProperty("jdbc.initSize", "1");
        props.setProperty("jdbc.maxSize", "15");
        //
        propsPersister.store(props, new FileOutputStream(application), "");
    }
    
    public File getTmpProjectLogo(Project project) {
        return new File(String.format("%s/project/%s/logo", 
                TMP_DIR, project.getId()));
    }
    
    public void storeTmpProjectLogo(InputStream inputStream, Project project) throws IOException {
        File tmpFile = new File(String.format("%s/project/%s/logo", 
                TMP_DIR, project.getId()));
        if(tmpFile.exists()) {
            tmpFile.delete();
        }
        tmpFile.getParentFile().mkdirs();
        tmpFile.createNewFile();
        FileCopyUtils.copy(inputStream, new FileOutputStream(tmpFile));
    }
    
    public File getProjectLogo(Project project) {
        return new File(String.format("%s/project/%s/logo.jpg", 
                RUNTIME_DIR, project.getId()));
    }
    
    public void storeProjectLogo(InputStream inputStream, Project project) throws IOException {
        File logoFile = new File(String.format("%s/project/%s/logo.jpg", 
                RUNTIME_DIR, project.getId()));
        if(logoFile.exists()) {
            logoFile.delete();
        }
        logoFile.getParentFile().mkdirs();
        logoFile.createNewFile();
        FileCopyUtils.copy(inputStream, new FileOutputStream(logoFile));
    }
    
    public void cropProjectLogo(Project project, String xStr, String yStr, String wStr, String hStr) throws IOException {
        File original = getTmpProjectLogo(project);
        File target = getProjectLogo(project);
        //
        if(xStr.contains(".")) {
            xStr = xStr.substring(0, xStr.indexOf("."));
        }
        if(yStr.contains(".")) {
            yStr = yStr.substring(0, yStr.indexOf("."));
        }
        if(wStr.contains(".")) {
            wStr = wStr.substring(0, wStr.indexOf("."));
        }
        if(hStr.contains(".")) {
            hStr = hStr.substring(0, hStr.indexOf("."));
        }
        //
        int x = NumberUtils.parseNumber(xStr, Integer.class);
        int y = NumberUtils.parseNumber(yStr, Integer.class);
        int w = NumberUtils.parseNumber(wStr, Integer.class);
        int h = NumberUtils.parseNumber(hStr, Integer.class);
        //
        ImageUtils.crop(original, target, x, y, w, h, "JPG");
    }
    
}
