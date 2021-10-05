package com.conversion.api.validation.annotations;

import com.conversion.api.validation.validators.CurrencyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Annotation used to validate currencies. Aggregates NotNull and NotEmpty.
 */
@Target({ FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CurrencyValidator.class)
@NotNull(message = "Missing currency value.")
@NotEmpty(message = "Currency value can not be empty.")
@Documented
public @interface Currency {

    String message() default "{Currencies.IncorrectCurrencyType}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}