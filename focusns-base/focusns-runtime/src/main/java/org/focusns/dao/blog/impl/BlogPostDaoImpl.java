/*
 * Copyright (C) 2012 FocusSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.focusns.dao.blog.impl;

import java.util.HashMap;
import java.util.Map;
import org.focusns.dao.blog.BlogPostDao;
import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.model.blog.BlogPost;
import org.focusns.model.common.Page;
import org.springframework.stereotype.Repository;

@Repository
public class BlogPostDaoImpl extends MyBatisBaseDao<BlogPost> implements BlogPostDao {

    public Page<BlogPost> fetchByTagId(Page<BlogPost> page, long tagId) {
        //
        Map parameter = new HashMap();
        parameter.put("tagId", tagId);
        //
        return fetchPage(".fetchByTagId", page, parameter);
    }

    public Page<BlogPost> fetchByProjectId(Page<BlogPost> page, long projectId) {
        //
        Map parameter = new HashMap();
        parameter.put("projectId", projectId);
        //
        return fetchPage(".fetchByProjectId", page, parameter);
    }
    
}
