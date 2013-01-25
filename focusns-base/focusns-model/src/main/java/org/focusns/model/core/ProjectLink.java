/*
 * Copyright (C) 2011-2013 FocusSNS.
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
package org.focusns.model.core;

import org.focusns.model.common.Id;

public class ProjectLink extends Id {
    
    private long fromProjectId;
    private long toProjectId;
    private boolean mutual;

    private Project fromProject;
    private Project toProject;

    public ProjectLink() {
    }

    public long getFromProjectId() {
        return fromProjectId;
    }

    public void setFromProjectId(long fromProjectId) {
        this.fromProjectId = fromProjectId;
    }

    public long getToProjectId() {
        return toProjectId;
    }

    public void setToProjectId(long toProjectId) {
        this.toProjectId = toProjectId;
    }
    
    public boolean isMutual() {
        return mutual;
    }

    public void setMutual(boolean mutual) {
        this.mutual = mutual;
    }

    public Project getFromProject() {
        return fromProject;
    }

    public void setFromProject(Project fromProject) {
        this.fromProject = fromProject;
    }

    public Project getToProject() {
        return toProject;
    }

    public void setToProject(Project toProject) {
        this.toProject = toProject;
    }
}
