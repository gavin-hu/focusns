package org.focusns.common.mail;

import org.focusns.common.mail.config.MailConfig;
import org.focusns.common.mail.config.MailFactory;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;
import java.util.Map;

public class DefaultMailService implements MailService {

    private MailFactory mailFactory;
    private JavaMailSender mailSender;

    public void setMailFactory(MailFactory mailFactory) {
        this.mailFactory = mailFactory;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(String messageId, Map<String, Object> context) {
        //
        MailConfig mailConfig = mailFactory.find(messageId);
        MimeMessage message = MailUtils.prepareMessage(mailSender, mailConfig, context);
        //
        mailSender.send(message);
    }

}
