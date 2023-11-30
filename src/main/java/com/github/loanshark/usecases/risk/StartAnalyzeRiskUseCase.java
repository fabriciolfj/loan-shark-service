package com.github.loanshark.usecases.risk;

import com.github.loanshark.entities.risk.Risk;

public interface StartAnalyzeRiskUseCase {

    void execute(final Risk risk);
}