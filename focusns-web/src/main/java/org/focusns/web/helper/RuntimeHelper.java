package org.focusns.web.helper;

/*
 * #%L
 * FocusSNS Web
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.util.FileCopyUtils;

public class RuntimeHelper {

    private static final String TMP_DIR = System.getProperty("java.io.tmpdir");

    private static final String RUNTIME_DIR = System.getProperty("user.home") + File.separator + ".focusns";

    private static final File application = new File(RUNTIME_DIR, "application.properties");

    private static RuntimeHelper instance = new RuntimeHelper();

    public static boolean isInstalled() {
        return application.exists();
    }

    public static boolean isTempFileExists(Coordinate coordinate) {
        return isFileExists(TMP_DIR, coordinate);
    }

    public static File getTempFile(Coordinate coordinate) throws IOException {
        return getFile(TMP_DIR, coordinate);
    }

    public static void setTempFile(Coordinate coordinate, InputStream inputStream) throws IOException {
        setFile(TMP_DIR, coordinate, inputStream);
    }

    public static void cleanTempFile(Coordinate coordinate) {
        File file = new File(TMP_DIR, coordinate.toPath());
        file.delete();
    }

    public static boolean isTargetFileExists(Coordinate coordinate) {
        return isFileExists(RUNTIME_DIR, coordinate);
    }

    public static File getTargetFile(Coordinate coordinate) throws IOException {
        return getFile(RUNTIME_DIR, coordinate);
    }

    public static void setTargetFile(Coordinate coordinate, InputStream inputStream) throws IOException {
        setFile(RUNTIME_DIR, coordinate, inputStream);
    }

    public static void cleanTargetFile(Coordinate coordinate) {
        File file = new File(RUNTIME_DIR, coordinate.toPath());
        File parentFile = file.getParentFile();
        //
        String baseCoordinate = coordinate.toPath(false) + "_";
        if (parentFile != null && parentFile.listFiles() != null) {
            for (File tmp : parentFile.listFiles()) {
                if (tmp.getAbsolutePath().contains(baseCoordinate)) {
                    tmp.delete();
                }
            }
        }
        //
        file.delete();
    }

    private static boolean isFileExists(String rootDir, Coordinate coordinate) {
        File file = new File(rootDir, coordinate.toPath());
        return file.exists();
    }

    private static File getFile(String rootDir, Coordinate coordinate) throws IOException {
        File file = new File(rootDir, coordinate.toPath());
        return file;
    }

    private static void setFile(String rootDir, Coordinate coordinate, InputStream inputStream) throws IOException {
        // override
        File file = new File(rootDir, coordinate.toPath());
        if (file.exists()) {
            file.delete();
        }
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileCopyUtils.copy(inputStream, new FileOutputStream(file));
    }

}
