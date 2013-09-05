package org.focusns.security;

import java.util.HashMap;
import java.util.Map;

public class Authorities {

    public static final String PROJECT_PROFILE_VIEW = "project-profile-view";
    public static final String PROJECT_PROFILE_EDIT = "project-profile-edit";

    public static final String PROJECT_BLOG_VIEW = "project-blog-view";
    public static final String PROJECT_BLOG_EDIT = "project-blog-edit";

    public static final String PROJECT_PHOTO_VIEW = "project-photo-view";
    public static final String PROJECT_PHOTO_EDIT = "project-photo-edit";

    public static final String PROJECT_TEAM_VIEW = "project-team-view";
    public static final String PROJECT_TEAM_EDIT = "project-team-edit";

    public static final String PROJECT_ADMIN_VIEW = "project-admin-view";
    public static final String PROJECT_ADMIN_EDIT = "project-admin-edit";
    //
    private static final Map<String, String[]> authorityMap = new HashMap<String, String[]>();

    static {
        authorityMap.put("profile", new String[]{PROJECT_PROFILE_VIEW, PROJECT_PROFILE_EDIT});
        authorityMap.put("blog", new String[]{PROJECT_BLOG_VIEW, PROJECT_BLOG_EDIT});
        authorityMap.put("photo", new String[]{PROJECT_PHOTO_VIEW, PROJECT_PHOTO_EDIT});
        authorityMap.put("team", new String[]{PROJECT_TEAM_VIEW, PROJECT_TEAM_EDIT});
        authorityMap.put("admin", new String[]{PROJECT_ADMIN_VIEW, PROJECT_ADMIN_EDIT});
    }

    public Map<String, String[]> getAuthorityMap() {
        return authorityMap;
    }

}
