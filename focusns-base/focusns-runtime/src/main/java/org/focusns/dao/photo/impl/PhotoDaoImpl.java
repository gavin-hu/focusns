package org.focusns.dao.photo.impl;

import java.util.List;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.photo.PhotoDao;
import org.focusns.model.photo.Photo;
import org.springframework.stereotype.Repository;

@Repository
public class PhotoDaoImpl extends MyBatisBaseDao<Photo> implements PhotoDao {

    public List<Photo> selectList(long albumId) {
        return getSqlSession().selectList(NAMESPACE.concat(".selectList"), albumId);
    }
}
