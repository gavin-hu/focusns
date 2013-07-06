package org.focusns.common.exception;

public enum ServiceExceptionCode implements ExceptionCode {

    PASSWORD_MISS_MATCH() {
        public int getStatus() {
            return 1000;
        }
    }

}
