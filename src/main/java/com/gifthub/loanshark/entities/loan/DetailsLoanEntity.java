package com.gifthub.loanshark.entities.loan;

import java.math.BigDecimal;
import java.time.LocalDate;

record DetailsLoanEntity(BigDecimal value, LocalDate requestDate) {
}
