package com.gifthub.loanshark.entities.risk;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public record RiskEntity(String loan, int score, Optional<BigDecimal> committedValue, Optional<LocalDate> dateLastLoan) {
}
