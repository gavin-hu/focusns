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

package org.focusns.service.photo;

import org.focusns.model.photo.Photo;
import org.focusns.service.AbstractServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class PhotoServiceTest extends AbstractServiceTest {

    @Autowired
    private PhotoService photoService;

    @Test
    public void testCreatePhoto() {
         Photo photo = new Photo();
        photo.setAlbumId(1);
        photo.setCreateAt(new Date());
        photo.setCreateById(1);
        photo.setProjectId(1);
        //
        photoService.createPhoto(photo);
    }

}
