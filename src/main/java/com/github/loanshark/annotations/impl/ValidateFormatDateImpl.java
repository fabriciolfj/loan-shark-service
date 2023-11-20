package com.github.loanshark.annotations.impl;

import com.github.loanshark.annotations.ValidateFormatDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ValidateFormatDateImpl implements ConstraintValidator<ValidateFormatDate, String> {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return false;
        }

        try {
            LocalDate.parse(value, DATE_FORMAT);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
