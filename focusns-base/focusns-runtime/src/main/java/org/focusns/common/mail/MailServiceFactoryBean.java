package org.focusns.common.mail;

import org.focusns.common.mail.config.xml.XmlMailFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.StringUtils;

import java.util.Properties;

public class MailServiceFactoryBean extends AbstractFactoryBean {

    private String host;
    private int port;
    private String protocol;
    //
    private String username;
    private String password;
    private Properties mailProperties;
    //
    private String mailConfigLocation = "classpath*:/mails";

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMailProperties(Properties mailProperties) {
        this.mailProperties = mailProperties;
    }

    public void setMailLocation(String mailConfigLocation) {
        this.mailConfigLocation = mailConfigLocation;
    }

    @Override
    public Class<?> getObjectType() {
        return MailService.class;
    }

    @Override
    protected Object createInstance() throws Exception {
        XmlMailFactory mailFactory = new XmlMailFactory(mailConfigLocation);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setJavaMailProperties(mailProperties);
        if(port>0) {
            mailSender.setPort(port);
        }
        if(StringUtils.hasText(protocol)) {
            mailSender.setProtocol(protocol);
        }
        //
        DefaultMailService mailService = new DefaultMailService();
        mailService.setMailSender(mailSender);
        mailService.setMailFactory(mailFactory);
        //
        return mailService;
    }
}
