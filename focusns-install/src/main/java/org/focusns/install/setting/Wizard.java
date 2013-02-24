package org.focusns.install.setting;

import java.util.Scanner;

import org.focusns.install.utils.Properties;

/**
 * Setting Wizard
 * @author Gavin Hu
 * @since 2.0
 */
public interface Wizard {

    void setup(Scanner scanner, Properties globalSettings) throws Exception;

}
