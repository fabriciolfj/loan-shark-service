package com.gifthub.loanshark.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public record RiskEntity(int score, Optional<BigDecimal> committedValue, Optional<LocalDate> dateLastLoan) {
}
