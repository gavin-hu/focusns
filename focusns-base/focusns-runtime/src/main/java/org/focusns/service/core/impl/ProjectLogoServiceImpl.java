package org.focusns.service.core.impl;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */


import org.focusns.dao.core.ProjectLogoDao;
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

    public void cropProjectLogoImage(Project project, File original) throws IOException {
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
