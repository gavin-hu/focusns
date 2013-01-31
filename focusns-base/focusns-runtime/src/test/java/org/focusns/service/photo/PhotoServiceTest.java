

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
