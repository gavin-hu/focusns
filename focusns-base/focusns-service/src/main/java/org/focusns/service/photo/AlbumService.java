
package org.focusns.service.photo;

import org.focusns.model.photo.Album;

import java.util.List;

public interface AlbumService {

    void createAlbum(Album album);
    
    void modifyAlbum(Album album);
    
    void removeAlbum(Album album);

    List<Album> listAlbums(long projectId);
    
}
