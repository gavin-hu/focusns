

package org.focusns.install.setting;

import org.focusns.install.utils.Properties;

import java.util.Scanner;

/**
 * A wizard for database setting
 * @author Gavin Hu
 * @since 2.0
 */
public class DatabaseWizard implements Wizard {
    private String REGEX_IP = "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$";
    private String REGEX_PORT = "^([0-9]|[1-9]\\d|[1-9]\\d{2}|[1-9]\\d{3}|[1-5]\\d{4}|6[0-4]\\d{3}|65[0-4]\\d{2}|655[0-2]\\d|6553[0-5])$";
    private String JDBC_URL = "jdbc:%s://%s:%s/%s";

    private String type;
    private String host;
    private String port;
    private String database;
    private String username;
    private String password;

    @Override
    public void setup(Scanner scanner, Properties globalSettings) {
        printWelcome();
        //
        scanType(scanner);
        //
        scanHost(scanner);
        //
        scanPort(scanner);
        //
        scanDatabase(scanner);
        //
        scanUsername(scanner);
        //
        scanPassword(scanner);
        //
        printSettingInfo();
        //
        config(globalSettings);
    }

    /**
     * Output welcome info to console
     */
    private void printWelcome() {
        StringBuilder welcomeBuilder = new StringBuilder();
        welcomeBuilder.append("\n###################################\n");
        welcomeBuilder.append("#                                 #\n");
        welcomeBuilder.append("#            数据库设置            #\n");
        welcomeBuilder.append("#                                 #\n");
        welcomeBuilder.append("###################################\n");
        System.out.println(welcomeBuilder);
    }

    /**
     * Scan database type from console
     * @param scanner
     */
    private void scanType(Scanner scanner) {
        System.out.print("请输入数据库类型（可选 [mysql]- 默认：mysql）：");
        String type = scanner.nextLine();
        // set default type
        if("".equalsIgnoreCase(type)) {
            type = "mysql";
        }
        //
        if(!"mysql".equalsIgnoreCase(type)) {
            System.out.println("FocusSNS当前只支持MySQL数据库！");
            scanType(scanner);
        } else {
            System.out.print(String.format("您输入的数据库类型为 %s 【Y/N】：", type));
            String yesOrNo = scanner.nextLine();
            if(!"Y".equalsIgnoreCase(yesOrNo)) {
                scanType(scanner);
            } else {
                this.type = type;
            }
        }
    }

    /**
     * Scan database host from console
     * @param scanner
     */
    private void scanHost(Scanner scanner) {
        System.out.print("请输入数据库IP（默认：127.0.0.1）：");
        String host = scanner.nextLine();
        // set default host
        if("".equals(host)) {
            host = "127.0.0.1";
        }
        //
        if(host.matches(REGEX_IP)) {
            System.out.print(String.format("您输入的数据库IP为 %s 【Y/N】：", host));
            String yesOrNo = scanner.nextLine();
            if(!"Y".equalsIgnoreCase(yesOrNo)) {
                scanHost(scanner);
            } else {
                this.host = host;
            }
        } else {
            System.out.println("您输入的数据库地址非法，请重新输入！");
            scanHost(scanner);
        }
    }

    /**
     * Scan database port from console
     * @param scanner
     */
    private void scanPort(Scanner scanner) {
        System.out.print("请输入数据库端口（默认为：3306）：");
        String port = scanner.nextLine();
        // set default port
        if("".equals(port)) {
            port = "3306";
        }
        //
        if(port.matches(REGEX_PORT)) {
            System.out.print(String.format("您输入的数据库端口为 %s 【Y/N】：", port));
            String yesOrNo = scanner.nextLine();
            if(!"Y".equalsIgnoreCase(yesOrNo)) {
                scanPort(scanner);
            } else {
                this.port = port;
            }
        } else {
            System.out.println("您输入的数据库端口非法，请重新输入！");
            scanPort(scanner);
        }
    }

    /**
     * Scan database schema from console
     * @param scanner
     */
    private void scanDatabase(Scanner scanner) {
        System.out.print("请输入数据库名称：");
        String database = scanner.nextLine();
        if("".equals(database.trim())) {
            System.out.println("数据库名称不能为空！");
            scanDatabase(scanner);
            return;
        }
        //
        System.out.print(String.format("您输入的数据库名称为 %s 【Y/N】：", database));
        String yesOrNo = scanner.nextLine();
        if(!"Y".equalsIgnoreCase(yesOrNo)) {
            scanDatabase(scanner);
        } else {
            this.database = database;
        }
    }

    /**
     * Scan database username from console
     * @param scanner
     */
    private void scanUsername(Scanner scanner) {
        System.out.print("请输入数据库用户名：");
        String username = scanner.nextLine();
        if("".equals(username.trim())) {
            System.out.println("数据库用户名不能为空！");
            scanUsername(scanner);
            //
            return;
        }
        //
        System.out.print(String.format("您输入的数据库用户名为 %s 【Y/N】：", username));
        String yesOrNo = scanner.nextLine();
        if(!"Y".equalsIgnoreCase(yesOrNo)) {
            scanUsername(scanner);
        } else {
            this.username = username;
        }
    }

    /**
     * Scan database password from console
     * @param scanner
     */
    private void scanPassword(Scanner scanner) {
        System.out.print("请输入数据库密码：");
        String password = scanner.nextLine();
        System.out.print(String.format("您输入的数据库密码为 %s 【Y/N】：",
                "".equals(password.trim())?"空":password));
        String yesOrNo = scanner.nextLine();
        if(!"Y".equalsIgnoreCase(yesOrNo)) {
            scanPassword(scanner);
        } else {
            this.password = password;
        }
    }

    private void printSettingInfo() {
        StringBuilder databaseInfo = new StringBuilder();
        databaseInfo.append("+--------------------------+\n");
        databaseInfo.append("   数据库类型：").append(type).append("\n");
        databaseInfo.append("   数据库主机：").append(host).append("\n");
        databaseInfo.append("   数据库端口：").append(port).append("\n");
        databaseInfo.append("   数据库名称：").append(database).append("\n");
        databaseInfo.append("   数据库账号：").append(username).append("\n");
        databaseInfo.append("   数据库密码：").append(password).append("\n");
        databaseInfo.append("+--------------------------+");
        //
        System.out.println(databaseInfo);
    }

    private Properties config(Properties globalSettings) {
        globalSettings.setProperty("jdbc.driver", parseJdbcDriver());
        globalSettings.setProperty("jdbc.url", parseJdbcUrl());
        globalSettings.setProperty("jdbc.username", username);
        globalSettings.setProperty("jdbc.password", password);
        globalSettings.setProperty("jdbc.initSize", "5");
        globalSettings.setProperty("jdbc.maxSize", "20");
        return globalSettings;
    }

    private String parseJdbcDriver() {
        if("mysql".equalsIgnoreCase(type)) {
            return "com.mysql.jdbc.Driver";
        }
        //
        throw new UnsupportedOperationException(
                String.format("Database %s is not supported yet!", type)
        );
    }

    private String parseJdbcUrl() {
        return String.format(JDBC_URL, type, host, port, database);
    }
}
