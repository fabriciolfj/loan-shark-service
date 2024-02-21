package com.github.loanshark.usecases.risk.providers;


import com.github.loanshark.entities.loan.Loan;
import com.github.loanshark.entities.risk.Risk;

public interface FetchLoanDataProvider {

    Loan process(final Risk risk);
}
