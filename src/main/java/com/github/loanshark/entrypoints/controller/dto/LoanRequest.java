package com.github.loanshark.entrypoints.controller.dto;

import com.github.loanshark.annotations.ValidateFormatDate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record LoanRequest(
        @NotEmpty(message = "{loanRequest.name}") String name,
        @NotEmpty(message = "{loanRequest.document}") String document,
        @ValidateFormatDate(message = "{loanRequest.birthdayInvalid}")
        @NotEmpty(message = "{loanRequest.birthday}") String birthday,
        @Positive(message = "{loanRequest.loan}") BigDecimal loan) {
}
