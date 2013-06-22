package org.focusns.service.common.impl;

import org.focusns.service.common.TempStorageService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class LocalTempStorageService extends LocalStorageService implements TempStorageService {

    @Override
    public long checkTempResource(Object... coordinates) throws IOException {
        return super.checkResource(getTempCoordinates(coordinates));
    }

    @Override
    public InputStream loadTempResource(Object... coordinates) throws IOException {
        return super.loadResource(getTempCoordinates(coordinates));
    }

    @Override
    public void persistTempResource(InputStream inputStream, Object... coordinates) throws IOException {
        super.persistResource(inputStream, getTempCoordinates(coordinates));
    }

    @Override
    public void removeTempResource(Object... coordinates) throws IOException {
        super.removeResource(getTempCoordinates(coordinates));
    }

    private Object[] getTempCoordinates(Object...coordinates) {
        return prependCoordinate("temp", coordinates);
    }

    private Object[] prependCoordinate(Object preCoordinate, Object[] coordinates) {
        int size = coordinates.length + 1;
        Object[] tmp = new Object[coordinates.length+1];
        tmp[0] = preCoordinate;
        for(int i=1; i<size; i++) {
            tmp[i] = coordinates[i-1];
        }
        return tmp;
    }
}
