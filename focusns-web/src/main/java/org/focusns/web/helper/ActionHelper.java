package org.focusns.web.helper;

public class ActionHelper {
    
    public static boolean isFetch(String action) {
        return "fetch".equalsIgnoreCase(action);
    }
    
    public static boolean isCreate(String action) {
        return "create".equalsIgnoreCase(action);
    }
    
    public static boolean isModify(String action) {
        return "modify".equalsIgnoreCase(action);
    }
    
    public static boolean isRemove(String action) {
        return "remove".equalsIgnoreCase(action);
    }
}
