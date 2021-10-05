package com.conversion.api.validation.exceptions;

/**
 * Exception thrown when the filter validation fails.
 */
public class InvalidFilterException extends IllegalArgumentException {
    /**
     * Constructs an InvalidFilterException with the specified detail message.
     * @param s   the detail message.
     */
    public InvalidFilterException(String s) {
        super(s);
    }
}
