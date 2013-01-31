

package org.focusns.install.setting;


import org.focusns.install.utils.Properties;

/**
 * Setting Wizard
 * @author Gavin Hu
 * @since 2.0
 */
public interface Wizard {

    void setup(Properties globalSettings) throws Exception;

}
