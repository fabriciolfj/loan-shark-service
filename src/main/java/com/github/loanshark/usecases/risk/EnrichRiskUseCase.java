package com.github.loanshark.usecases.risk;

import com.github.loanshark.entities.risk.Risk;

public interface EnrichRiskUseCase {

    Risk execute(final Risk risk);
}
