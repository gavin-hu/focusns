package org.focusns.dao.blog;

/*
 * #%L
 * FocusSNS Dao
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

import org.focusns.dao.common.BaseDao;
import org.focusns.model.blog.BlogPost;
import org.focusns.model.common.Page;

public interface BlogPostDao extends BaseDao<BlogPost> {

    /**
     * 
     * @param page
     * @param projectId
     *            #See Project
     * @param categoryId
     *            #See BlogCategory
     * @return
     */
    Page<BlogPost> selectByProjectAndCategoryId(Page<BlogPost> page, Long projectId, Long categoryId);

}
