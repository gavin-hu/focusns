package org.focusns.common.web.page.config;

public class PageConfigException extends RuntimeException {
    public PageConfigException(String message) {
        super(message);
    }

    public PageConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageConfigException(Throwable cause) {
        super(cause);
    }
}
