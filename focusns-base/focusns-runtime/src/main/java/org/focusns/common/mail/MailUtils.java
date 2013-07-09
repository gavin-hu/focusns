package org.focusns.common.mail;

import org.focusns.common.mail.config.MailConfig;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;
import java.util.Map;

class MailUtils {

    public static MimeMessage prepareMessage(JavaMailSender mailSender, MailConfig mailConfig, Map<String, Object> context) {

        return null;
    }

}
