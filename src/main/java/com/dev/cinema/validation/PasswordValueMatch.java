package com.dev.cinema.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValueMatch {
    String message() default "Password and repeat password don't match";

    String password();

    String repeatPassword();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}