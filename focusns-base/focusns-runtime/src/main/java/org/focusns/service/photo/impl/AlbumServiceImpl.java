

package org.focusns.service.photo.impl;

import org.focusns.dao.photo.AlbumDao;
import org.focusns.model.photo.Album;
import org.focusns.service.photo.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao albumDao;

    @Override
    public void createAlbum(Album album) {
        this.albumDao.insert(album);
    }

    @Override
    public void modifyAlbum(Album album) {
        this.albumDao.update(album);
    }

    @Override
    public void removeAlbum(Album album) {
        this.albumDao.delete(album.getId());
    }

    @Override
    public List<Album> listAlbums(long projectId) {
        return albumDao.selectList(projectId);
    }
}
