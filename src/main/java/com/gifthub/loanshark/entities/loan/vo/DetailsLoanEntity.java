package com.gifthub.loanshark.entities.loan.vo;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DetailsLoanEntity(BigDecimal value, LocalDate requestDate) {
}
