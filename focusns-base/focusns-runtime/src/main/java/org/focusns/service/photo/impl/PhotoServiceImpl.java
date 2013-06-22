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

import org.focusns.dao.core.ProjectDao;
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.dao.photo.AlbumDao;
import org.focusns.dao.photo.PhotoDao;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.model.photo.Album;
import org.focusns.model.photo.Photo;
import org.focusns.service.photo.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDao photoDao;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectUserDao projectUserDao;

    @Override
    public Photo getPhoto(long photoId) {
        Photo photo = photoDao.select(photoId);
        return fillPhoto(photo);
    }

    public void createPhoto(Photo photo) {
        if(photo.getCreatedAt()==null) {
            photo.setCreatedAt(new Date());
        }
        this.photoDao.insert(photo);
        fillPhoto(photo);
    }

    public void modifyPhoto(Photo photo) {
        if(photo.getCreatedAt()==null) {
            photo.setCreatedAt(new Date());
        }
        this.photoDao.update(photo);
        fillPhoto(photo);
    }

    public void removePhoto(Photo photo) {
        this.photoDao.delete(photo.getId());
        fillPhoto(photo);
    }

    public List<Photo> listPhoto(long albumId) {
        List<Photo> photos = photoDao.selectList(albumId);
        for(Photo photo : photos) {
            fillPhoto(photo);
        }
        return photos;
    }

    private Photo fillPhoto(Photo photo) {
        if(photo==null) {
            return photo;
        }
        //
        if(photo.getAlbum()==null && photo.getAlbumId()>0) {
            Album album = albumDao.select(photo.getAlbumId());
            photo.setAlbum(album);
        }
        if(photo.getProject()==null && photo.getProjectId()>0) {
            Project project = projectDao.select(photo.getProjectId());
            photo.setProject(project);
        }
        if(photo.getCreatedBy()==null && photo.getCreatedById()>0) {
            ProjectUser createdBy = projectUserDao.select(photo.getCreatedById());
            photo.setCreatedBy(createdBy);
        }
        //
        return photo;
    }
}
