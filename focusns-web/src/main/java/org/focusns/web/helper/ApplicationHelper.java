package org.focusns.web.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.focusns.common.image.ImageUtils;
import org.focusns.model.common.Rectangle;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.NumberUtils;

public class ApplicationHelper {

    private static final String TMP_DIR = System.getProperty("java.io.tmpdir");
    
    private static final String RUNTIME_DIR = System.getProperty("user.home") + File.separator + ".focusns";

    private static final File application = new File(RUNTIME_DIR, "application.properties");
    
    private static ApplicationHelper instance = new ApplicationHelper();

    private ApplicationHelper() {
    }
    
    public static ApplicationHelper getInstance() {
        return instance;
    }

    public boolean isInstalled() {
        return application.exists();
    }

    public File getTmpProjectLogo(String tmpId) {
        return new File(String.format("%s/project/logo/%s", TMP_DIR, tmpId));
    }
    
    public void storeTmpProjectLogo(InputStream inputStream, String tmpId) throws IOException {
        File tmpFile = new File(String.format("%s/project/logo/%s", TMP_DIR, tmpId));
        if(tmpFile.exists()) {
            tmpFile.delete();
        }
        tmpFile.getParentFile().mkdirs();
        tmpFile.createNewFile();
        FileCopyUtils.copy(inputStream, new FileOutputStream(tmpFile));
    }
    
    public File getProjectLogo(long projectId) {
        return new File(String.format("%s/project/%s/logo.jpg", RUNTIME_DIR, projectId));
    }
    
    public void storeProjectLogo(InputStream inputStream, long projectId) throws IOException {
        File logoFile = new File(String.format("%s/project/%s/logo.jpg", RUNTIME_DIR, projectId));
        if(logoFile.exists()) {
            logoFile.delete();
        }
        logoFile.getParentFile().mkdirs();
        logoFile.createNewFile();
        FileCopyUtils.copy(inputStream, new FileOutputStream(logoFile));
    }
    
    public void cropProjectLogo(File original, File target, Rectangle rectangle) throws IOException {
        //
        String xStr = rectangle.getX();
        String yStr = rectangle.getY();
        String wStr = rectangle.getW();
        String hStr = rectangle.getH();
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
