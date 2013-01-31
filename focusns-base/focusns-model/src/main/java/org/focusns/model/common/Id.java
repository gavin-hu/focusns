
package org.focusns.model.common;

import java.io.Serializable;

public abstract class Id implements Serializable {

	private long id;

    public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
}
