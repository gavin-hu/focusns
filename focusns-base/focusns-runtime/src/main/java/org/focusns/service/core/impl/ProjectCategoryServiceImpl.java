package org.focusns.service.core.impl;

import java.util.List;

import org.focusns.dao.core.ProjectCategoryDao;
import org.focusns.model.core.ProjectCategory;
import org.focusns.service.core.ProjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectCategoryServiceImpl implements ProjectCategoryService {

    @Autowired
    private ProjectCategoryDao categoryDao;
    
    public ProjectCategory getCategory(long categoryId) {
        return categoryDao.select(categoryId);
    }

    public ProjectCategory getCategory(String code) {
    	return categoryDao.selectByCode(code);
    }
    
    public void createCategory(ProjectCategory category) {
        categoryDao.insert(category);
    }

    public void modifyCategory(ProjectCategory category) {
        categoryDao.update(category);
    }
    
    public void removeCategory(ProjectCategory category) {
    	categoryDao.delete(category.getId());
    }

    public List<ProjectCategory> listCategories(boolean isPublic) {
        return categoryDao.selectList(isPublic);
    }
    
}
