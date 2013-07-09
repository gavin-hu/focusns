package org.focusns.common.exception;

public enum ServiceExceptionCode implements ExceptionCode {

    PASSWORD_MISS_MATCH() {
        public int getStatus() {
            return 1000;
        }
    },
    MAIL_SEND_EXCEPTION() {
        public int getStatus() {
            return 2000;
        }
    }

}
