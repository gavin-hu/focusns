package org.focusns.dao.photo;

import java.util.List;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.photo.Album;

public interface AlbumDao extends BaseDao<Album> {

    List<Album> selectList(long projectId);
}
