package org.focusns.install.utils;

/*
 * #%L
 * FocusSNS Install
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FileCopyUtils {

    public static boolean copyFile(final java.io.File toCopy, final java.io.File destFile) {
        try {
            return FileCopyUtils.copyStream(new FileInputStream(toCopy), new FileOutputStream(destFile));
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean copyFilesRecusively(final File toCopy, final File destDir) {
        assert destDir.isDirectory();

        if (!toCopy.isDirectory()) {
            return FileCopyUtils.copyFile(toCopy, new File(destDir, toCopy.getName()));
        } else {
            final File newDestDir = new File(destDir, toCopy.getName());
            if (!newDestDir.exists() && !newDestDir.mkdir()) {
                return false;
            }
            for (final File child : toCopy.listFiles()) {
                if (!FileCopyUtils.copyFilesRecusively(child, newDestDir)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean copyJarResourcesRecursively(final File destDir, final JarURLConnection jarConnection)
            throws IOException {

        final JarFile jarFile = jarConnection.getJarFile();

        for (final Enumeration<JarEntry> e = jarFile.entries(); e.hasMoreElements();) {
            final JarEntry entry = e.nextElement();
            if (entry.getName().startsWith(jarConnection.getEntryName())) {
                final String filename = removeStart(entry.getName(), //
                        jarConnection.getEntryName());

                final File f = new File(destDir, filename);
                if (!entry.isDirectory()) {
                    final InputStream entryInputStream = jarFile.getInputStream(entry);
                    if (!FileCopyUtils.copyStream(entryInputStream, f)) {
                        return false;
                    }
                    entryInputStream.close();
                } else {
                    if (!FileCopyUtils.ensureDirectoryExists(f)) {
                        throw new IOException("Could not create directory: " + f.getAbsolutePath());
                    }
                }
            }
        }
        return true;
    }

    public static boolean copyResourcesRecursively(final URL originUrl, final File destination) {
        try {
            final URLConnection urlConnection = originUrl.openConnection();
            if (urlConnection instanceof JarURLConnection) {
                return FileCopyUtils.copyJarResourcesRecursively(destination, (JarURLConnection) urlConnection);
            } else {
                return FileCopyUtils.copyFilesRecusively(new File(originUrl.getPath()), destination);
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean copyStream(final InputStream is, final File f) {
        try {
            return FileCopyUtils.copyStream(is, new FileOutputStream(f));
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean copyStream(final InputStream is, final OutputStream os) {
        try {
            final byte[] buf = new byte[1024];

            int len = 0;
            while ((len = is.read(buf)) > 0) {
                os.write(buf, 0, len);
            }
            is.close();
            os.close();
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean ensureDirectoryExists(final File f) {
        return f.exists() || f.mkdir();
    }

    private static String removeStart(String str, String remove) {
        if ((str == null || "".equals(str)) || (remove == null || "".equals(remove))) {
            return str;
        }
        if (str.startsWith(remove)) {
            return str.substring(remove.length());
        }
        return str;
    }
}
