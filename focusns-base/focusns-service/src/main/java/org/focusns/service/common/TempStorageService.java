package org.focusns.service.common;

import java.io.IOException;
import java.io.InputStream;

public interface TempStorageService extends StorageService {

    boolean existsTempResource(Object... coordinates) throws IOException;

    InputStream loadTempResource(Object...coordinates) throws IOException;

    void persistTempResource(InputStream inputStream, Object... coordinates) throws IOException;

    void removeTempResource(Object... coordinates) throws IOException;

}
