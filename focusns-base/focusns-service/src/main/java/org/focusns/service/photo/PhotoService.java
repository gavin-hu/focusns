package org.focusns.service.photo;

import org.focusns.model.photo.Photo;

import java.util.List;

public interface PhotoService {

    void createPhoto(Photo photo);
    
    void modifyPhoto(Photo photo);
    
    void removePhoto(Photo photo);
    
    List<Photo> listPhoto(long albumId);
    
}
