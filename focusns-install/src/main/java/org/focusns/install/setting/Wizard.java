

package org.focusns.install.setting;


import org.focusns.install.utils.Properties;

import java.util.Scanner;

/**
 * Setting Wizard
 * @author Gavin Hu
 * @since 2.0
 */
public interface Wizard {

    void setup(Scanner scanner, Properties globalSettings) throws Exception;

}
