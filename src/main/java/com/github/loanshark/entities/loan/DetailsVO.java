package com.github.loanshark.entities.loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record DetailsVO(BigDecimal value, LocalDateTime requestDate) {
}
