

package org.focusns.dao.photo.impl;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.photo.PhotoDao;
import org.focusns.model.photo.Photo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhotoDaoImpl extends MyBatisBaseDao<Photo> implements PhotoDao {

    @Override
    public List<Photo> selectList(long albumId) {
        return getSqlSession().selectList(NAMESPACE.concat(".selectList"), albumId);
    }
}
