package com.conversion.api.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A DTO that assists with wrapping the error message whenever an exception occurs.
 */
public class ErrorMessage {
    @JsonProperty("reason")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorMessage(){

    }

    public ErrorMessage(String message) {
        this.message = message;
    }
}
