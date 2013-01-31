

package org.focusns.install.setting;

/*
 * #%L
 * focusns-install
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */


import org.focusns.install.utils.Properties;

import java.util.Scanner;

public class ConsoleWizard implements Wizard {

    private String username;
    private String password;

    @Override
    public void setup(Scanner scanner, Properties globalSettings) throws Exception {
        //

        //
    }

    private void printWelcome() {
        StringBuilder welcome = new StringBuilder();
    }

    private void scanUsername(Scanner scanner) {

    }

    private void scanPassword(Scanner scanner) {

    }
}
