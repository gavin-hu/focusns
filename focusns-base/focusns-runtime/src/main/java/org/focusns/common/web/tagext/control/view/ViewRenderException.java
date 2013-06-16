package org.focusns.common.web.tagext.control.view;

public class ViewRenderException extends RuntimeException {

    public ViewRenderException(String message) {
        super(message);
    }

    public ViewRenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ViewRenderException(Throwable cause) {
        super(cause);
    }
}
