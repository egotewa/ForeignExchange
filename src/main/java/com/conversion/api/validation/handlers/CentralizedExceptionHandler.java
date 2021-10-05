package com.conversion.api.validation.handlers;

import com.conversion.api.representation.ErrorMessage;
import com.conversion.api.validation.exceptions.InvalidCurrencyException;
import com.conversion.api.validation.exceptions.InvalidFilterException;
import com.conversion.api.validation.exceptions.NoRecordsFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * A centralized exception handler that copes with multiple types of exceptions and returns
 * accurate responses to the end users.
 */
@ControllerAdvice
public class CentralizedExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles exceptions that occur due to constraint violations.
     * @param ex    The exception that occurred.
     * @param request   The request which triggered the exception.
     * @return  Response entity with a rightful response code and a wrapped error message.
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintErrors(
            ConstraintViolationException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorMessage(ex.getLocalizedMessage()),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handles exceptions that occur due to argument validation failures.
     * @param ex    The exception that occurred.
     * @param request   The request which triggered the exception.
     * @return  Response entity with a rightful response code and a wrapped error message.
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentErrors(
            IllegalArgumentException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorMessage(ex.getLocalizedMessage()),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handles exceptions that occur due to common validation failures.
     * @param ex    The exception that occurred.
     * @param request   The request which triggered the exception.
     * @return  Response entity with a rightful response code and a wrapped error message.
     */
    @ExceptionHandler(value = ValidationException.class)
    protected ResponseEntity<Object> handleValidationErrors(
            ValidationException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorMessage("Invalid parameter value passed. Please check your request. " +
                                            "Additional information: " + ex.getCause().getLocalizedMessage()),
                                                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handles exceptions that occur due to currency validation failures.
     * @param ex    The exception that occurred.
     * @param request   The request which triggered the exception.
     * @return  Response entity with a rightful response code and a wrapped error message.
     */
    @ExceptionHandler(value = InvalidCurrencyException.class)
    protected ResponseEntity<Object> handleInvalidCurrencyErrors(
            InvalidCurrencyException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorMessage("Invalid currency passed."),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handles exceptions that occur due to filter validation failures.
     * @param ex    The exception that occurred.
     * @param request   The request which triggered the exception.
     * @return  Response entity with a rightful response code and a wrapped error message.
     */
    @ExceptionHandler(value = InvalidFilterException.class)
    protected ResponseEntity<Object> handleInvalidFilterErrors(
            InvalidFilterException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorMessage(ex.getLocalizedMessage()),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handles exceptions that occur when a database fetch returns no records.
     * @param ex    The exception that occurred.
     * @param request   The request which triggered the exception.
     * @return  Response entity with a rightful response code and a wrapped error message.
     */
    @ExceptionHandler(value = NoRecordsFoundException.class)
    protected ResponseEntity<Object> handleNoResultErrors(
            NoRecordsFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorMessage(ex.getLocalizedMessage()),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    /**
     * Handles exceptions that occur due to number formatting failures.
     * @param ex    The exception that occurred.
     * @param request   The request which triggered the exception.
     * @return  Response entity with a rightful response code and a wrapped error message.
     */
    @ExceptionHandler(value = NumberFormatException.class)
    protected ResponseEntity<Object> handleInvalidNumberFormatErrors(
            NumberFormatException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorMessage("Incorrect request parameter value detected - " + ex.getLocalizedMessage()),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handles general exceptions.
     * @param ex    The exception that occurred.
     * @param request   The request which triggered the exception.
     * @return  Response entity with a rightful response code.
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleGeneralErrors(
            Exception ex, WebRequest request) {
        String message = "The server was unable to comply with your request.";
        return handleExceptionInternal(ex, message,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    /**
     * Handles exceptions that occur due to argument validation errors.
     * @param ex    The exception that occurred.
     * @param request   The request which triggered the exception.
     * @return  Response entity with a rightful response code and a wrapped error message.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        return this.handleExceptionInternal(ex,
                new ErrorMessage("Invalid parameter values passed. Additional information: "
                        + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()),
                headers,
                status,
                request);
    }

    /**
     * Handles exceptions that occur due to an invalid request method.
     * @param ex    The exception that occurred.
     * @param request   The request which triggered the exception.
     * @return  Response entity with a rightful response code and a wrapped error message.
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatus status,
                                                                         WebRequest request) {
        Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
        if (!CollectionUtils.isEmpty(supportedMethods)) {
            headers.setAllow(supportedMethods);
        }

        return handleExceptionInternal(ex, new ErrorMessage("Request method type not supported."),
                headers,
                status,
                request);
    }

    /**
     * Handles exceptions that occur due to a missing request parameter.
     * @param ex    The exception that occurred.
     * @param request   The request which triggered the exception.
     * @return  Response entity with a rightful response code and a wrapped error message.
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status,
                                                                          WebRequest request) {
        return handleExceptionInternal(ex, new ErrorMessage(ex.getLocalizedMessage()),
                headers,
                status,
                request);
    }

}