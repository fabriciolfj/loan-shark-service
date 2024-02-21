package com.github.loanshark.usecases.risk.providers;

import com.github.loanshark.entities.risk.Risk;

public interface GetDetailsCustomerProvider {

    Risk process(final Risk risk);
}