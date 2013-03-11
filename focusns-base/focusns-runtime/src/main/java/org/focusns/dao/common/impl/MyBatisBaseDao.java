package org.focusns.dao.common.impl;

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


import org.apache.ibatis.session.RowBounds;
import org.focusns.dao.common.BaseDao;
import org.focusns.model.common.Page;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.util.List;
import java.util.Map;

public abstract class MyBatisBaseDao<M> extends SqlSessionDaoSupport implements BaseDao<M> {

    protected String NAMESPACE;
    
    public MyBatisBaseDao() {
    	inspectNamespace();
    }
   
    public M select(long id) {
        return getSqlSession().selectOne(NAMESPACE.concat(".select"), id);
    }

    public int insert(M model) {
        return getSqlSession().insert(NAMESPACE.concat(".insert"), model);
    }

    public int update(M model) {
        return getSqlSession().update(NAMESPACE.concat(".update"), model);
    }

    public int delete(long id) {
        return getSqlSession().delete(NAMESPACE.concat(".delete"), id);
    }

    public List<M> select(M model) {
        return getSqlSession().selectList(NAMESPACE.concat(".selectList"), model);
    }

    public List<M> selectList(String selectId, Object parameter) {
        return getSqlSession().selectList(NAMESPACE.concat(selectId), parameter);
    }
    
    public Page<M> fetchPage(String selectId, Page<M> page, Map<String, Object> model) {
        //
        if(page.isAutoCount()) {
            String countId = selectId.concat("Count");
            long totalCount = fetchPageCount(countId, model);
            page.setTotalCount(totalCount);
        }
        //
        RowBounds rowBounds = new RowBounds(page.getFirst()-1, page.getPageSize());
        List<M> results = getSqlSession().selectList(NAMESPACE.concat(selectId), model, rowBounds);
        //
        return page.setResults(results);
    }
    
    public long fetchPageCount(String countId, Map<String, Object> model) {
        Long count = getSqlSession().selectOne(NAMESPACE.concat(countId), model);
        return count;
    }
    
    private void inspectNamespace() {
    	Class<?>[] interfaceClasses = ClassUtils.getAllInterfaces(this);
    	for(Class<?> interfaceClass : interfaceClasses) {
    		if(ClassUtils.isAssignable(BaseDao.class, interfaceClass) && interfaceClass != BaseDao.class) {
    			this.NAMESPACE = interfaceClass.getName();
    		}
    	}
    	//
    	Assert.notNull(NAMESPACE, "Custom dao interface must implements BaseDao interface");
    }
    
}
