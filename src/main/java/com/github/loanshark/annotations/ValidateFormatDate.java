package com.github.loanshark.annotations;


import com.github.loanshark.annotations.impl.ValidateFormatDateImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidateFormatDateImpl.class})
public @interface ValidateFormatDate {

    String message();
    Class<?>[] groups()  default {};
    Class<? extends Payload>[] payload() default {};
}
