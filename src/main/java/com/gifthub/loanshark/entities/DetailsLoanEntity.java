package com.gifthub.loanshark.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DetailsLoanEntity(BigDecimal value, LocalDate requestDate) {
}
