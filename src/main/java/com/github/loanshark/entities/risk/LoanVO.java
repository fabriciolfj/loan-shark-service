package com.github.loanshark.entities.risk;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LoanVO(String code, BigDecimal value, LocalDateTime dateRequest) {
}
