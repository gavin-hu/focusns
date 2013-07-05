package org.focusns.service.common.impl;

import org.focusns.common.image.ImageUtils;
import org.focusns.common.io.FileUtils;
import org.focusns.service.common.StorageService;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.*;

@Service
public class LocalStorageService implements StorageService {

    protected static final String LOCAL_STORAGE_DIR = System.getProperty("user.home") + File.separator + ".focusns";

    protected ResourceLoader resourceLoader = new FileSystemResourceLoader();

    @Override
    public long checkResource(Object... coordinates) throws IOException {
        Resource resource = resourceLoader.getResource(getResourceLocation(coordinates));
        return resource.exists() ? resource.lastModified() : 0;
    }

    @Override
    public InputStream loadResource(Object... coordinates) throws IOException {
        Resource resource = resourceLoader.getResource(getResourceLocation(coordinates));
        if(!resource.exists()) {
            createResource(resource);
        }
        return resource.getInputStream();
    }

    private int[] parseStringSize(String size) {
        int[] wh = new int[2];
        String[] parts = StringUtils.split((String) size, "x");
        wh[0] = Integer.parseInt(parts[0]);
        wh[1] = Integer.parseInt(parts[1]);
        return wh;
    }

    @Override
    public long persistResource(InputStream inputStream, Object... coordinates) throws IOException {
        Resource resource = resourceLoader.getResource(getResourceLocation(coordinates));
        if(!resource.exists()) {
            createResource(resource);
        }
        FileCopyUtils.copy(inputStream, new FileOutputStream(resource.getFile()));
        return resource.lastModified();
    }

    @Override
    public void removeResource(Object... coordinates) throws IOException {
        Resource resource = resourceLoader.getResource(getResourceLocation(coordinates));
        if(resource.exists()) {
            resource.getFile().delete();
        }
    }

    @Override
    public InputStream loadSizedResource(Object size, Object... coordinates) throws IOException {
        if(size instanceof String && ((String)size).contains("x")) {
            int[] wh = parseStringSize((String)size);
            //
            Resource sizedResource = resourceLoader.getResource(getSizedResourceLocation(size, coordinates));
            if(sizedResource.exists()==false) {
                createResource(sizedResource);
                //
                Resource resource = resourceLoader.getResource(getResourceLocation(coordinates));
                ImageUtils.resize(resource.getFile(), sizedResource.getFile(), wh[0], wh[1], "PNG");
            }
            return sizedResource.getInputStream();
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeSizedResource(Object size, Object... coordinates) throws IOException {
        Resource sizedResource = resourceLoader.getResource(getSizedResourceLocation(size, coordinates));
        if(sizedResource.exists()) {
            sizedResource.getFile().delete();
        }
    }

    @Override
    public void cleanSizedResource(Object... coordinates) throws IOException {
        Resource resource = resourceLoader.getResource(getResourceLocation(coordinates));
        String fileName = resource.getFilename();
        File fileDir = resource.getFile().getParentFile();
        for(File file : fileDir.listFiles()) {
            if(file.getName().startsWith(fileName+"_")) {
                file.delete();
            }
        }
    }

    @Override
    public long checkSizedResource(Object size, Object... coordinates) throws IOException {
        Resource sizedResource = resourceLoader.getResource(getSizedResourceLocation(size, coordinates));
        return sizedResource.exists()?sizedResource.lastModified() : 0;
    }

    private void createResource(Resource resource) throws IOException {
        resource.getFile().getParentFile().mkdirs();
        resource.getFile().createNewFile();
    }

    private String getSizedResourceLocation(Object size, Object... coordinates) {
        String resourceLocation = getResourceLocation(coordinates);
        return resourceLocation + "_" + size;
    }

    private String getResourceLocation(Object... coordinates) {
        StringBuilder pathBuilder = new StringBuilder(LOCAL_STORAGE_DIR);
        for(Object coordinate : coordinates) {
            pathBuilder.append(File.separator).append(coordinate);
        }
        return pathBuilder.toString();
    }
}
