package com.github.loanshark.usecases.loan.providers;

import com.github.loanshark.entities.loan.Loan;

public interface SendProcessLoanProvider {

    void process(final Loan loan);
}
