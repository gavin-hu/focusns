package org.focusns.service.core;

import org.focusns.model.common.Rectangle;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectLogo;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ProjectLogoService { 
    
    ProjectLogo getProjectLogo(long logoId);
    
    void createProjectLogo(ProjectLogo logo);

    void modifyProjectLogo(ProjectLogo logo);
    
    void removeProjectLogo(ProjectLogo logo);
    
    List<ProjectLogo> listProjectLogos(long projectId);
    
    File loadProjectLogoImage(ProjectLogo logo);
    
    void cropProjectLogoImage(Project project, File original, Rectangle rectangle) throws IOException;
    
}
