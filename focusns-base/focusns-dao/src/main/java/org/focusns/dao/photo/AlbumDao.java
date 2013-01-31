
package org.focusns.dao.photo;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.photo.Album;

import java.util.List;

public interface AlbumDao extends BaseDao<Album> {

    List<Album> selectList(long projectId);
}
