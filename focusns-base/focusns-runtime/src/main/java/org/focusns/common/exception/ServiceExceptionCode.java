package org.focusns.common.exception;

public enum ServiceExceptionCode implements ExceptionCode {

    PASSWORD_MISS_MATCH() {
        public int getStatus() {
            return 1000;
        }
    },
    PROJECT_USER_ALREADY_EXIST() {
        @Override
        public int getStatus() {
            return 1100;
        }
    },
    MAIL_SEND_EXCEPTION() {
        public int getStatus() {
            return 2000;
        }
    },
    PROJECT_CATEGORY_UNSUPPORTED() {
        public int getStatus() {
            return 2100;
        }
    },
    PROJECT_LINK_ALREADY_EXIST {
        public int getStatus() {
            return 2200;
        }
    }

}
