package org.focusns.dao.photo.impl;

import java.util.List;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.photo.AlbumDao;
import org.focusns.model.photo.Album;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumDaoImpl extends MyBatisBaseDao<Album> implements AlbumDao {

    public List<Album> selectList(long projectId) {
        return getSqlSession().selectList(NAMESPACE.concat(".selectList"), projectId);
    }
}
