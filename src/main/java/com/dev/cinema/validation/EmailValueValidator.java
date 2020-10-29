package com.dev.cinema.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValueValidator implements ConstraintValidator<EmailValueConstraint, String> {
    private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-"
            + "]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*"
            + "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email.matches(EMAIL_REGEX);
    }
}
