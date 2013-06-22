package org.focusns.service.common.helper;

import org.focusns.model.core.ProjectUser;
import org.focusns.model.photo.Photo;

public abstract class CoordinateHelper {

    public static Object[] getAvatarCoordinates(ProjectUser projectUser) {
        return new Object[]{"project", projectUser.getProjectId(), "avatar", projectUser.getId()};
    }

    public static Object[] getPhotoCoordinates(Photo photo) {
        return new Object[]{"project", photo.getProjectId(), "photo", photo.getId()};
    }

}
