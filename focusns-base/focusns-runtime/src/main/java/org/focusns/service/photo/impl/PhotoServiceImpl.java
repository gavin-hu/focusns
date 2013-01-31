

package org.focusns.service.photo.impl;

import org.focusns.dao.photo.PhotoDao;
import org.focusns.model.photo.Photo;
import org.focusns.service.photo.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDao photoDao;

    @Override
    public void createPhoto(Photo photo) {
        this.photoDao.insert(photo);
    }

    @Override
    public void modifyPhoto(Photo photo) {
        this.photoDao.update(photo);
    }

    @Override
    public void removePhoto(Photo photo) {
        this.photoDao.delete(photo.getId());
    }

    @Override
    public List<Photo> listPhoto(long albumId) {
        return photoDao.selectList(albumId);
    }
}
