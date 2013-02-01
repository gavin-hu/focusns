package org.focusns.web.helper;

import java.util.Properties;

public class PropertyHelper {

    public static String getConsoleUsername(Properties props) {
        return props.getProperty("console.username");
    }

    public static String getConsolePassword(Properties props) {
        return props.getProperty("console.password");
    }

}
