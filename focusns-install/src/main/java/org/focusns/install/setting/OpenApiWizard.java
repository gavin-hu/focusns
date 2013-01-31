

package org.focusns.install.setting;

import org.focusns.install.utils.Properties;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class OpenApiWizard implements Wizard {

    private String[] providers = new String[]{"sina", "renren", "tencent"};

    private Map<String, Map<String, String>> openAPIProviders = new LinkedHashMap<String, Map<String, String>>();

    @Override
    public void setup(Scanner scanner, Properties globalSettings) {
        printWelcome();
        //
        for(String provider : providers) {
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
