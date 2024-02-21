package com.github.loanshark.usecases.risk.providers;

import com.github.loanshark.entities.risk.Risk;

public interface PersistRiskProvider {

    Risk process(final Risk risk);
}
