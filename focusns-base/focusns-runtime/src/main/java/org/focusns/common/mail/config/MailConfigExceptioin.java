package org.focusns.common.mail.config;

public class MailConfigExceptioin extends RuntimeException {

    public MailConfigExceptioin() {
    }

    public MailConfigExceptioin(String s) {
        super(s);
    }

    public MailConfigExceptioin(String s, Throwable throwable) {
        super(s, throwable);
    }

    public MailConfigExceptioin(Throwable throwable) {
        super(throwable);
    }
}
