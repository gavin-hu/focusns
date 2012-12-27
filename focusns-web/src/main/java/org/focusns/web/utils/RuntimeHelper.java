/*
 * Copyright (C) 2012 FocuSNS.
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
package org.focusns.web.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletContext;
import org.focusns.common.image.ImageUtils;
import org.focusns.model.core.Project;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.NumberUtils;

public class RuntimeHelper {

    private String TMP_DIR;
    
    private String RUNTIME_DIR;
    
    private static ServletContext servletContext;

    public RuntimeHelper(ServletContext servletContext) {
        RuntimeHelper.servletContext = servletContext;
        //
        this.TMP_DIR = System.getProperty("java.io.tmpdir");
        this.RUNTIME_DIR = servletContext.getRealPath("/WEB-INF/runtime");
    }
    
    public static RuntimeHelper getInstance() {
        return (RuntimeHelper) RuntimeHelper.servletContext
                .getAttribute(RuntimeHelper.class.getName());
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
