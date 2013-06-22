package org.focusns.service.photo.impl;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import java.util.Date;
import java.util.List;

import org.focusns.dao.core.ProjectDao;
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.dao.photo.AlbumDao;
import org.focusns.dao.photo.PhotoDao;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.model.photo.Album;
import org.focusns.model.photo.Photo;
import org.focusns.service.photo.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private PhotoDao photoDao;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectUserDao projectUserDao;

    @Override
    public Album getAlbum(long albumId) {
        Album album = albumDao.select(albumId);
        return fillAlbum(album);
    }

    public void createAlbum(Album album) {
        Date now = new Date();
        if(album.getCreatedAt()==null) {
            album.setCreatedAt(now);
        }
        if(album.getModifiedAt()==null) {
            album.setModifiedAt(now);
        }
        this.albumDao.insert(album);
        //
        fillAlbum(album);
    }

    public void modifyAlbum(Album album) {
        this.albumDao.update(album);
        fillAlbum(album);
    }

    public void removeAlbum(Album album) {
        this.albumDao.delete(album.getId());
        fillAlbum(album);
    }

    public List<Album> listAlbums(long projectId) {
        List<Album> albums = albumDao.selectList(projectId);
        for(Album album : albums) {
            fillAlbum(album);
        }
        return albums;
    }

    private Album fillAlbum(Album album) {
        if (album==null) {
            return album;
        }
        //
        if(album.getPhoto()==null && album.getPhotoId()>0) {
            Photo photo = photoDao.select(album.getPhotoId());
            album.setPhoto(photo);
        }
        if(album.getProject()==null && album.getProjectId()>0) {
            Project project = projectDao.select(album.getProjectId());
            album.setProject(project);
        }
        if(album.getCreatedBy()==null && album.getCreatedById()>0) {
            ProjectUser createdBy = projectUserDao.select(album.getCreatedById());
            album.setCreatedBy(createdBy);
        }
        if(album.getModifiedBy()==null && album.getModifiedById()>0) {
            ProjectUser modifiedBy = projectUserDao.select(album.getModifiedById());
            album.setModifiedBy(modifiedBy);
        }
        //
        return album;
    }
}
