
package org.focusns.service.core.impl;

import org.focusns.dao.core.ProjectLogoDao;
import org.focusns.model.common.Rectangle;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectLogo;
import org.focusns.service.core.ProjectLogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ProjectLogoServiceImpl implements ProjectLogoService {

    @Autowired
    private ProjectLogoDao logoDao;
    
    public void createProjectLogo(ProjectLogo logo) {
        logoDao.insert(logo);
    }

    public ProjectLogo getProjectLogo(long logoId) {
        return logoDao.select(logoId);
    }

    public void modifyProjectLogo(ProjectLogo logo) {
        logoDao.update(logo);
    }

    public void removeProjectLogo(ProjectLogo logo) {
        logoDao.delete(logo.getId());
    }

    public List<ProjectLogo> listProjectLogos(long projectId) {
        return logoDao.selectList(projectId);
    }

    public File loadProjectLogoImage(ProjectLogo logo) {
//        return RuntimeHelper.getInstance().getProjectLogo(logo);
        return null;
    }

    public void cropProjectLogoImage(Project project, File original, Rectangle rectangle) throws IOException {
        //
      /*  ProjectLogo logo = new ProjectLogo();
        logo.setProjectId(project.getId());
        logoDao.insert(logo);
        //
        File target = RuntimeHelper.getInstance().getProjectLogo(logo);
        ImageUtils.crop(original, target, rectangle.getXInt(), rectangle.getYInt(),
                rectangle.getWInt(), rectangle.getHInt(), "JPG");*/
    }
    
}
