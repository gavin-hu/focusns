package org.focusns.service.core.impl;

import org.focusns.dao.core.ProjectDao;
import org.focusns.dao.core.ProjectLinkDao;
import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectLink;
import org.focusns.service.core.ProjectLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectLinkServiceImpl implements ProjectLinkService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectLinkDao linkDao;

    
    public ProjectLink getProjectLink(long id) {
        return linkDao.select(id);
    }

    public void createProjectLink(ProjectLink link) {
        ProjectLink dbLink = linkDao.selectByFromAndToProjectId(link.getFromProjectId(), link.getToProjectId());
        if(dbLink==null) {
            linkDao.insert(link);
        } else {
            throw new RuntimeException("The link already exist!");
        }
    }

    public void modifyProjectLink(ProjectLink link) {
        linkDao.update(link);
    }

    public void removeProjectLink(ProjectLink link) {
        linkDao.delete(link.getId());
    }

    public void removeProjectLink(long fromProjectId, long toProjectId) {
        linkDao.deleteByFromAndToProjectId(fromProjectId, toProjectId);
    }

    public ProjectLink getProjectLink(long fromProjectId, long toProjectId) {
        return linkDao.selectByFromAndToProjectId(fromProjectId, toProjectId);
    }

    public Page<ProjectLink> fetchPageByToProjectId(Page<ProjectLink> page, long toProjectId) {
        page = linkDao.fetchByToProjectId(page, toProjectId, null);
        for(ProjectLink projectLink : page.getResults()) {
            Project fromProject = projectDao.select(projectLink.getFromProjectId());
            Project toProject = projectDao.select(projectLink.getToProjectId());
            projectLink.setFromProject(fromProject);
            projectLink.setToProject(toProject);
        }
        return page;
    }

    public Page<ProjectLink> fetchPageByFromProjectId(Page<ProjectLink> page, long fromProjectId) {
        page = linkDao.fetchByFromProjectId(page, fromProjectId, null);
        for(ProjectLink projectLink : page.getResults()) {
            Project fromProject = projectDao.select(projectLink.getFromProjectId());
            Project toProject = projectDao.select(projectLink.getToProjectId());
            projectLink.setFromProject(fromProject);
            projectLink.setToProject(toProject);
        }
        return page;
    }

}
