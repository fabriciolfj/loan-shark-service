package com.github.loanshark.entities.risk;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CustomerVO(LocalDate birthdayCustomer, BigDecimal salaryCustomer) {
}
