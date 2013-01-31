
package org.focusns.dao.common;

public interface BaseDao<M> {

	M select(long id);
	
	int insert(M model);
	
	int update(M model);
	
	int delete(long id);
    
}
