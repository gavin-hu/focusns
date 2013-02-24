package org.focusns.dao.core.impl;

import java.util.HashMap;
import java.util.Map;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.core.ProjectLinkDao;
import org.focusns.model.common.Page;
import org.focusns.model.core.ProjectLink;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectLinkDaoImpl extends MyBatisBaseDao<ProjectLink>
    implements ProjectLinkDao {

    public ProjectLink selectByFromAndToProjectId(long fromProjectId, long toProjectId) {
        //
        Map parameter = createParameter(fromProjectId, toProjectId, null);
        //
        return getSqlSession().selectOne(NAMESPACE.concat(".selectByFromAndToProjectId"), parameter);
    }

    public void deleteByFromAndToProjectId(long fromProjectId, long toProjectId) {
        //
        Map parameter = createParameter(fromProjectId, toProjectId, null);
        //
        getSqlSession().delete(NAMESPACE.concat(".deleteByFromAndToProjectId"), parameter);
    }

    public Page<ProjectLink> fetchByToProjectId(Page<ProjectLink> page, Long toProjectId, Boolean mutual) {
        //
        Map parameter = createParameter(null, toProjectId, mutual);
        //
        return fetchPage(".fetchPage", page, parameter);
    }

    public Page<ProjectLink> fetchByFromProjectId(Page<ProjectLink> page, Long fromProjectId, Boolean mutual) {
        //
        Map parameter = createParameter(fromProjectId, null, mutual);
        //
        return fetchPage(".fetchPage", page, parameter);
    }

    private Map createParameter(Long fromProjectId, Long toProjectId, Boolean mutual) {
        Map parameter = new HashMap();
        //
        parameter.put("fromProjectId", fromProjectId);
        parameter.put("toProjectId", toProjectId);
        parameter.put("mutual", mutual);
        //
        return parameter;
    }
}
