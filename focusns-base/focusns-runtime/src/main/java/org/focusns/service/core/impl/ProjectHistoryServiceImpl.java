package org.focusns.service.core.impl;

import java.util.Date;
import java.util.List;

import org.focusns.dao.core.ProjectDao;
import org.focusns.dao.core.ProjectHistoryDao;
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.model.common.Page;
import org.focusns.model.core.ProjectHistory;
import org.focusns.service.core.ProjectHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectHistoryServiceImpl implements ProjectHistoryService {

    @Autowired
    private ProjectUserDao projectUserDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectHistoryDao projectHistoryDao;
    
    public void createProjectHistory(ProjectHistory histroy) {
        //
        Date now = new Date();
        histroy.setCreateAt(now);
        //
        projectHistoryDao.insert(histroy);
    }

    public void modifyProjectHistory(ProjectHistory history) {
        projectHistoryDao.update(history);
    }

    public void removeProjectHistory(ProjectHistory history) {
        projectHistoryDao.delete(history.getId());
    }

    public Page<ProjectHistory> fetchPage(Page<ProjectHistory> page, long projectId) {
        page = projectHistoryDao.fetchByProjectId(page, projectId);
        //
        for(ProjectHistory history : page.getResults()) {
            List<ProjectHistory> children = projectHistoryDao
                    .selectByParentId(history.getId());
            history.setChildren(children);
        }
        //
        return page;
    }
    
}
