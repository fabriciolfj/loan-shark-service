package com.github.loanshark.usecases.risk;

import com.github.loanshark.entities.risk.Risk;

public interface ProcessAnalyseRiskUseCase {

    Risk execute(final Risk risk);
}
