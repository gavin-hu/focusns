
package org.focusns.service.core.impl;

import org.focusns.dao.core.ProjectAttributeDao;
import org.focusns.model.core.ProjectAttribute;
import org.focusns.service.core.ProjectAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectAttributeServiceImpl implements ProjectAttributeService {
    
    @Autowired
    private ProjectAttributeDao attributeDao;

    public void createProjectAttribute(ProjectAttribute attribute) {
        attributeDao.insert(attribute);
    }

    public void modifyProjectAttribute(ProjectAttribute attribute) {
        attributeDao.update(attribute);
    }

    public void removeProjectAttribute(ProjectAttribute attribute) {
        attributeDao.delete(attribute.getId());
    }

    public List<ProjectAttribute> getProjectAttributes(long projectId) {
        return attributeDao.selectByProjectId(projectId);
    }
    
}
