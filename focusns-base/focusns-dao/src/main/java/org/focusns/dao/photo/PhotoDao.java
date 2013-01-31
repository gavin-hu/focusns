
package org.focusns.dao.photo;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.photo.Photo;

import java.util.List;

public interface PhotoDao extends BaseDao<Photo> {

    List<Photo> selectList(long albumId);
}
