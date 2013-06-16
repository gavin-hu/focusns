package org.focusns.common.web.page.engine;

public class PageEngineException extends RuntimeException {

    public PageEngineException(String message) {
        super(message);
    }

    public PageEngineException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageEngineException(Throwable cause) {
        super(cause);
    }
}
