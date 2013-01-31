

package org.focusns.install.setting;

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
