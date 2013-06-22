package org.focusns.service.common;

import java.io.IOException;
import java.io.InputStream;

public interface StorageService {

    long checkResource(Object... coordinates) throws IOException;

    InputStream loadResource(Object...coordinates) throws IOException;

    long persistResource(InputStream inputStream, Object... coordinates) throws IOException;

    void removeResource(Object... coordinates) throws IOException;

    long checkSizedResource(Object size, Object...coordinates) throws IOException;

    InputStream loadSizedResource(Object size, Object...coordinates) throws IOException;

    void removeSizedResource(Object size, Object...coordinates) throws IOException;

    void cleanSizedResource(Object...coordinates) throws IOException;

}
