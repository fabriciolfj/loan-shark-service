package com.github.loanshark.entities.risk;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CustomerVO(String document, LocalDate birthdayCustomer, BigDecimal salaryCustomer) {
}
