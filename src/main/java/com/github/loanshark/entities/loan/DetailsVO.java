package com.github.loanshark.entities.loan;

import java.math.BigDecimal;
import java.time.LocalDate;

record DetailsVO(BigDecimal value, LocalDate requestDate) {
}
