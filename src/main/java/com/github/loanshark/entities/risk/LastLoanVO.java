package com.github.loanshark.entities.risk;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LastLoanVO(LocalDate lastRequest, BigDecimal value) {
}