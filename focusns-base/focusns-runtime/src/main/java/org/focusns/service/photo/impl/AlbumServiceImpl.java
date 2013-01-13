/*
 * Copyright (C) 2011-2013 FocusSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

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
