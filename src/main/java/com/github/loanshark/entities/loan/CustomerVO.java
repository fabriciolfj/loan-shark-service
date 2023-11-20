package com.github.loanshark.entities.loan;

import java.math.BigDecimal;
import java.time.LocalDate;

record CustomerVO(String name, String document, LocalDate birthday, BigDecimal salary) {
}
