package org.focusns.common.mail;

import java.util.Map;

public interface MailService {

    void send(String messageId, Map<String, Object> context);

    void send(String messageId, String[] to, Map<String, Object> context);

}
