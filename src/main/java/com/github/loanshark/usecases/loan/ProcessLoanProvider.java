package com.github.loanshark.usecases.loan;

import com.github.loanshark.entities.loan.Loan;

public interface ProcessLoanProvider {

    void process(final Loan loan);
}
