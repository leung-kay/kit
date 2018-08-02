package com.ruifucredit.cloud.kit.exception;


public class Rexception extends RuntimeException {

    public Rexception() {
        super();
    }

    public Rexception(String message) {
        super(message);
    }

    public Rexception(String message, Throwable cause) {
        super(message, cause);
    }

    public Rexception(Throwable cause) {
        super(cause);
    }
}
