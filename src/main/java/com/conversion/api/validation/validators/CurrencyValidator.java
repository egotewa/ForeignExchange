package com.conversion.api.validation.validators;

import com.conversion.api.validation.annotations.Currency;
import com.conversion.api.validation.exceptions.InvalidCurrencyException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

/**
 * Validator used when verifying currency values.
 */
public class CurrencyValidator implements ConstraintValidator<Currency, String> {

    /** All valid currencies according to their ISO 4217 codes. */
    private static final Set<java.util.Currency> mAvailableCurrencies = java.util.Currency.getAvailableCurrencies();

    /**
     * Validates the currency value.
     * @param value     the currency value.
     * @param context   the validator context.
     * @return  true if the value is acceptable, false otherwise.
     * @throws InvalidCurrencyException in case an error occurs while mapping the currency value.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            return mAvailableCurrencies.contains(java.util.Currency.getInstance(value));
        } catch (Exception ex) {
            throw new InvalidCurrencyException("Invalid currency type passed: " + value);
        }
    }
}
