

package org.focusns.service.photo;

import org.focusns.model.photo.Album;
import org.focusns.service.AbstractServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class AlbumServiceTest extends AbstractServiceTest {

    @Autowired
    private AlbumService albumService;

    @Test
    public void createAlbum() {
         Album album = new Album();
         album.setLabel("label");
         album.setCreateAt(new Date());
         album.setCreateById(1);
         album.setProjectId(1);
         //
        albumService.createAlbum(album);
    }

}
