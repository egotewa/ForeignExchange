package com.conversion.api.utils;

import com.conversion.api.constants.ParameterType;
import com.conversion.api.validation.exceptions.InvalidFilterException;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.EnumSet;

/**
 * Util class to assist with filter validation.
 */
public class FilterUtils {

    /**
     * Checks the filter and assigns a type to the value depending on the filter.
     * @param filter    the filter.
     * @param value     the filter value.
     * @param <T>       type representing all possible (and future) filter value types.
     * @return An object which wraps the value.
     */
    public static <T> T determineFilterValueType(String filter, String value) {
        ParameterType parameterType;
        try {
            parameterType = ParameterType.valueOf(filter);
        } catch (Exception ex) {
            throw new InvalidFilterException(MessageFormat.format(
                    "Invalid filter provided: {0}. Valid values: {1}", filter, EnumSet.allOf(ParameterType.class)));
        }

        switch (parameterType) {
            case transactionId:
                return (T) Long.valueOf(value);
            case transactionDate:
                return (T) LocalDate.parse(value);
            default:
                return null;
        }
    }
}
