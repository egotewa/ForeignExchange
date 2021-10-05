package com.conversion.api.validation.exceptions;

/**
 * Exception thrown when a database fetch returns no records.
 */
public class NoRecordsFoundException extends IllegalArgumentException {
    /**
     * Constructs an NoRecordsFoundException with the specified detail message.
     * @param s   the detail message.
     */
    public NoRecordsFoundException(String s) {
        super(s);
    }
}
