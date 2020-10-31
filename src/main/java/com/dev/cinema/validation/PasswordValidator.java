package com.dev.cinema.validation;

import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordValidator implements ConstraintValidator<PasswordValueMatch, Object> {
    private String password;
    private String repeatPassword;

    public void initialize(PasswordValueMatch constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.repeatPassword = constraintAnnotation.repeatPassword();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(password);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(repeatPassword);
        return Objects.equals(fieldValue, fieldMatchValue);
    }
}
