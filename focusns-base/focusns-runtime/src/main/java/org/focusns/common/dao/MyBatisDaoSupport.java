package org.focusns.common.dao;

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

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.focusns.dao.common.BaseDao;
import org.focusns.model.common.Page;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

public abstract class MyBatisDaoSupport<M> extends SqlSessionDaoSupport implements BaseDao<M> {

    protected String NAMESPACE;

    public MyBatisDaoSupport() {
        inspectNamespace();
    }

    public M select(long id) {
        return selectOne("select", id);
    }

    public int insert(M model) {
        return insert("insert", model);
    }

    public int update(M model) {
        return update("update", model);
    }

    public int delete(long id) {
        return delete("delete", id);
    }

    public int insert(String insertId, Object parameter)  {
        return getSqlSession().insert(NAMESPACE.concat(insertId), parameter);
    }

    public int update(String updateId, Object parameter)  {
        return getSqlSession().update(NAMESPACE.concat(updateId), parameter);
    }

    public int delete(String deleteId, Object parameter)  {
        return getSqlSession().delete(NAMESPACE.concat(deleteId), parameter);
    }

    public <M> M selectOne(String selectId, Object parameter) {
        return getSqlSession().selectOne(NAMESPACE.concat(selectId), parameter);
    }

    public List<M> selectList(String selectId, Object parameter) {
        return getSqlSession().selectList(NAMESPACE.concat(selectId), parameter);
    }

    public Page<M> selectPage(String selectId, Page<M> page, Map<String, Object> model) {
        //
        if (page.isAutoCount()) {
            String countId = selectId.concat("Count");
            long totalCount = selectPageCount(countId, model);
            page.setTotalCount(totalCount);
        }
        //
        RowBounds rowBounds = new RowBounds(page.getFirst() - 1, page.getPageSize());
        List<M> results = getSqlSession().selectList(NAMESPACE.concat(selectId), model, rowBounds);
        //
        return page.setResults(results);
    }

    public long selectPageCount(String countId, Map<String, Object> model) {
        Long count = getSqlSession().selectOne(NAMESPACE.concat(countId), model);
        return count;
    }

    private void inspectNamespace() {
        Class<?>[] interfaceClasses = ClassUtils.getAllInterfaces(this);
        for (Class<?> interfaceClass : interfaceClasses) {
            if (ClassUtils.isAssignable(BaseDao.class, interfaceClass) && interfaceClass != BaseDao.class) {
                this.NAMESPACE = interfaceClass.getName();
            }
        }
        //
        Assert.notNull(NAMESPACE, "Custom dao interface must implements BaseDao interface");
        //
        this.NAMESPACE += ".";
    }

}
