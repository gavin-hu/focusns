
package org.focusns.common.image;

import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.ThumpnailRescaleOp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageUtils {
    
    /**
     * 裁剪图片
     * @param originalFile
     * @param targetFile
     * @param x
     * @param y
     * @param w
     * @param h
     * @param format
     * @throws IOException 
     */
    public static void crop(File originalFile, File targetFile, 
            int x, int y, int w, int h, String format) throws IOException {
        //
        if(targetFile.exists()) {
            targetFile.delete();
        }
        targetFile.getParentFile().mkdirs();
        targetFile.createNewFile();
        //
        crop(new FileInputStream(originalFile), new FileOutputStream(targetFile),
                x, y, w, h, format);
    }
    
    /**
     * 裁剪图片
     * @param originalStream
     * @param targetStream
     * @param x
     * @param y
     * @param w
     * @param h
     * @param format
     * @throws IOException 
     */
    public static void crop(InputStream originalStream, OutputStream targetStream, 
            int x, int y, int w, int h, String format) throws IOException {
        BufferedImage originalImage = ImageIO.read(originalStream);
        BufferedImage targetImage = originalImage.getSubimage(x, y, w, h);
        ImageIO.write(targetImage, format, targetStream);
    }

    /**
     * 图片缩放
     *
     * @param originalFile
     * @param thumnailFile
     * @param newWidth
     * @param newHeight
     * @param format
     */
    public static void resize(File originalFile, File thumnailFile,
            int newWidth, int newHeight, String format) throws IOException {
        resize(new FileInputStream(originalFile), new FileOutputStream(thumnailFile), 
                newWidth, newHeight, format);
    }

    /**
     * 图片缩放
     *
     * @param originalStream
     * @param thumbnailStream
     * @param newWidth
     * @param newHeight
     * @param format
     */
    public static void resize(InputStream originalStream, OutputStream thumbnailStream,
            int newWidth, int newHeight, String format) throws IOException {
        BufferedImage originalImage = ImageIO.read(originalStream);
        // 获得原始图片的宽度及高度
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        // 判断是否有必要缩放
        if (width > 0 || height > 0) {
            AdvancedResizeOp resizeOp = new ThumpnailRescaleOp(newWidth, newHeight);
            resizeOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Normal);
            BufferedImage thumbnailImage = resizeOp.filter(originalImage, null);
            ImageIO.write(thumbnailImage, format, thumbnailStream);
        }
    }
}
