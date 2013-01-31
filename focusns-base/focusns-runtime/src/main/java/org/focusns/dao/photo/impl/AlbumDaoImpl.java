

package org.focusns.dao.photo.impl;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.photo.AlbumDao;
import org.focusns.model.photo.Album;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlbumDaoImpl extends MyBatisBaseDao<Album> implements AlbumDao {

    @Override
    public List<Album> selectList(long projectId) {
        return getSqlSession().selectList(NAMESPACE.concat(".selectList"), projectId);
    }
}
