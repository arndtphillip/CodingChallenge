package org.parndt.processing;

public class ProcessingException extends Exception {
    public ProcessingException(String message, Throwable error) {
        super(message, error);
    }
}
