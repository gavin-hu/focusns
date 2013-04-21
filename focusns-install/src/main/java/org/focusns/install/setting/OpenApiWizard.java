package org.focusns.install.setting;

/*
 * #%L
 * FocusSNS Install
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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.focusns.install.utils.Properties;

public class OpenApiWizard implements Wizard {

    private String[] providers = new String[] { "sina", "renren", "tencent" };

    private Map<String, Map<String, String>> openAPIProviders = new LinkedHashMap<String, Map<String, String>>();

    public void setup(Scanner scanner, Properties globalSettings) {
        printWelcome();
        //
        for (String provider : providers) {
            //
            scanApiKey(scanner);
            //

            //
        }
        //
        printOpenAPIInfo();
    }

    private void printWelcome() {
        System.out.println("欢迎进入OpenAPI设置向导...");
    }

    private void printOpenAPIInfo() {
    }

    private void scanCallback(Scanner scanner) {

    }

    private void scanApiKey(Scanner scanner) {
    }

    private void scanApiSecret(Scanner scanner) {
    }

}
