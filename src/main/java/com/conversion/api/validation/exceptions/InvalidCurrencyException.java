package com.conversion.api.validation.exceptions;

/**
 * Exception thrown when the currency validation fails.
 */
public class InvalidCurrencyException extends IllegalArgumentException {

    /**
     * Constructs an InvalidCurrencyException with the specified detail message.
     * @param s   the detail message.
     */
    public InvalidCurrencyException(String s) {
        super(s);
    }
}
