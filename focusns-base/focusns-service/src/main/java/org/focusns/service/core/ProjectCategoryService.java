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
