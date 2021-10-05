package com.conversion.api;

import com.conversion.api.constants.ParameterType;
import com.conversion.api.utils.FilterUtils;
import com.conversion.api.validation.exceptions.InvalidFilterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;

/**
 * Unit tests for the FilterUtils class.
 */
@SpringBootTest
public class FilterUtilsTests {

    /**
     * Checks whether the return type of the determineFilterValueType method is Long in case the
     * filter type is transactionId.
     */
    @Test
    void testDetermineFilterValueTypeLong() {
        Assert.isInstanceOf(Long.class,
                    FilterUtils.determineFilterValueType(ParameterType.transactionId.name(), "1"));
    }

    /**
     * Checks whether the return type of the determineFilterValueType method is LocalDate in case the
     * filter type is transactionDate.
     */
    @Test
    void testDetermineFilterValueTypeLocalDate() {
        Assert.isInstanceOf(LocalDate.class,
                FilterUtils.determineFilterValueType(ParameterType.transactionDate.name(), "2021-01-01"));
    }

    /**
     * Checks whether an exception is thrown whenever invalid filter type is passed.
     */
    @Test
    void testDetermineFilterValueTypeThrowingException() {
        Assertions.assertThrows(InvalidFilterException.class,
                () -> FilterUtils.determineFilterValueType("iMustGoWrong", "iDontMatter"));
    }
}
