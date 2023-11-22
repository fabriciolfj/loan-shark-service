package com.github.loanshark.annotations.impl;

import com.github.loanshark.annotations.ValidateFormatDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

import static com.github.loanshark.util.StringToLocalDateUtil.toLocalDate;

public class ValidateFormatDateImpl implements ConstraintValidator<ValidateFormatDate, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return false;
        }

        try {
            toLocalDate(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
