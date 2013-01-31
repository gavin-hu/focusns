
package org.focusns.service.core;

import org.focusns.model.core.ProjectCategory;

import java.util.List;

public interface ProjectCategoryService {
    
    ProjectCategory getCategory(long id);
    
    ProjectCategory getCategory(String code);
    
    void createCategory(ProjectCategory category);
    
    void modifyCategory(ProjectCategory category);
    
    void removeCategory(ProjectCategory category);
    
    List<ProjectCategory> listCategories(boolean isPublic);
    
}
