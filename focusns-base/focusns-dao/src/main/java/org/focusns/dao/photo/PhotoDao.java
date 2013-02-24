package org.focusns.dao.photo;

import java.util.List;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.photo.Photo;

public interface PhotoDao extends BaseDao<Photo> {

    List<Photo> selectList(long albumId);
}
