package org.focusns.common.image;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */


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
