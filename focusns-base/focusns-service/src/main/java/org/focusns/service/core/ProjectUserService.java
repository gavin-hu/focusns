package org.focusns.service.core;

/*
 * #%L
 * FocusSNS Service
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

import org.focusns.model.common.Page;
import org.focusns.model.core.ProjectUser;

public interface ProjectUserService {

    ProjectUser getProjectUser(String username);

    ProjectUser getProjectUser(long userId);

    boolean existProjectUser(ProjectUser projectUser);

    void enableProjectUser(ProjectUser projectUser);

    void createProjectUser(ProjectUser projectUser);

    void removeProjectUser(ProjectUser projectUser);

    void modifyProjectUser(ProjectUser projectUser);

    Page<ProjectUser> fetchPage(Page<ProjectUser> page);

    void assignRole(long projectId, long userId, long roleId);

    void unassignRole(long projectId, long userId, long roleId);

}
