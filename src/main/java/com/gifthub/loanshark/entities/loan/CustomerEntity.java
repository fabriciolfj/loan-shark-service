package com.gifthub.loanshark.entities.loan;

import java.math.BigDecimal;
import java.time.LocalDate;

record CustomerEntity(String name, String document, LocalDate birthday, BigDecimal salary) {
}
