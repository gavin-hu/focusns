package org.focusns.event.photo.impl;

import org.focusns.common.event.annotation.Event;
import org.focusns.common.event.annotation.EventSubscriber;
import org.focusns.common.event.support.EventContext;
import org.focusns.dao.photo.AlbumDao;
import org.focusns.model.photo.Album;
import org.focusns.model.photo.Photo;
import org.springframework.beans.factory.annotation.Autowired;

@EventSubscriber
public class PhotoEventSubscriber {

    private AlbumDao albumDao;

    @Autowired
    public void setAlbumDao(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }

    @Event(on = "CREATE_PHOTO", point = Event.Point.AFTER)
    public void afterCreatePhoto(EventContext eventContext) {
        AlbumDao albumDao = eventContext.getApplicationContext().getBean(AlbumDao.class);
        //
        Photo photo = (Photo) eventContext.getArguments()[0];
        Album album = albumDao.select(photo.getAlbumId());
        album.setPhotoId(photo.getId());
        albumDao.update(album);
    }

}
