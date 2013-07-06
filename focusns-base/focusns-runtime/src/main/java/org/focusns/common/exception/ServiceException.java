package org.focusns.common.exception;

import org.springframework.core.NestedRuntimeException;

public class ServiceException extends NestedRuntimeException {

    private ExceptionCode code;

    public ServiceException(ExceptionCode code, String msg) {
        super(msg);
        this.code = code;
    }

    public ServiceException(ExceptionCode code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public ExceptionCode getCode() {
        return code;
    }
}
