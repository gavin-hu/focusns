

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


import org.focusns.install.utils.FileCopyUtils;
import org.focusns.install.utils.Properties;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

/**
 * A server wizard for install servlet server such as jetty, tomcat...
 * @author Gavin Hu
 * @since 2.0
 */
public class ServerWizard implements Wizard {

    // supported servers
    private String[] servers = new String[]{"tomcat"};
    //
    private String installPath;

    @Override
    public void setup(Scanner scanner, Properties globalSettings) throws Exception {
        //
        ClassLoader classLoader = getClass().getClassLoader();
        for(String server : servers) {
            //
            URL url = classLoader.getResource("META-INF/" + server);
            //
            if(url!=null) {
                printWelcome();
                //
                scanInstallPath(scanner);
                //
                intallServer(url, server);
            }
        }
    }

    private void printWelcome() {
        StringBuilder welcomeBuilder = new StringBuilder();
        welcomeBuilder.append("\n###################################\n");
        welcomeBuilder.append("#                                 #\n");
        welcomeBuilder.append("#             服务器安装           #\n");
        welcomeBuilder.append("#                                 #\n");
        welcomeBuilder.append("###################################\n");
        System.out.println(welcomeBuilder);
    }

    private void intallServer(URL source, String server) {
        File destFile = new File(installPath);
        FileCopyUtils.copyResourcesRecursively(source, destFile);
    }

    private void scanInstallPath(Scanner scanner) {
        //
        System.out.print("请输入FocusSNS安装目录：");
        String installPath = scanner.nextLine();
        //
        try {
            File targetIntallPath = new File(installPath);
            if(!targetIntallPath.exists()) {
                targetIntallPath.mkdir();
            }
            //
            System.out.print(String.format("您输入的安装目录为 %s 【Y/N】：", installPath));
            String yesOrNo = scanner.nextLine();
            if(!"Y".equalsIgnoreCase(yesOrNo)) {
                scanInstallPath(scanner);
            } else {
                this.installPath = installPath;
            }
        } catch (Exception e) {
            System.err.println("你输入的安装目录非法，请重新输入！");
            scanInstallPath(scanner);
        }
    }
}
