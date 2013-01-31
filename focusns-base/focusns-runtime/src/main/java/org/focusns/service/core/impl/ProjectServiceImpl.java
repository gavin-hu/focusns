
package org.focusns.service.core.impl;

import org.focusns.common.event.Event;
import org.focusns.common.event.annotation.Trigger;
import org.focusns.common.event.annotation.Triggers;
import org.focusns.dao.core.ProjectDao;
import org.focusns.model.common.Caches;
import org.focusns.model.core.Project;
import org.focusns.service.core.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;

    @Cacheable(value=Caches.PROJECT, key = "#id")
    public Project getProject(long id) {
        return projectDao.select(id);
    }
    
    @Cacheable(value=Caches.PROJECT, key="#code")
	public Project getProject(String code) {
		return projectDao.selectByCode(code);
	}
	
	public void createProject(Project project) {
		projectDao.insert(project);
	}
	
    @Caching(evict = {
            @CacheEvict(value=Caches.PROJECT, key="#project.id"),
            @CacheEvict(value=Caches.PROJECT, key="#project.code")
    })
	public void removeProject(Project project) {
		projectDao.delete(project.getId());
	}
    
    @Caching(evict = {
            @CacheEvict(value=Caches.PROJECT, key="#project.id"),
            @CacheEvict(value=Caches.PROJECT, key="#project.code")
    })
    @Triggers({
            @Trigger(event = Event.MODIFY, point = Trigger.Point.BEFORE),
            @Trigger(event = Event.MODIFIED, point = Trigger.Point.AFTER)
    })
    public void modifyProject(Project project) {
        projectDao.update(project);
    }
    
}
