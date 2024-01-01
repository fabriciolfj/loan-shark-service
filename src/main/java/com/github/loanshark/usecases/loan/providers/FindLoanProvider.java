package com.github.loanshark.usecases.loan.providers;

import com.github.loanshark.entities.loan.Loan;

public interface FindLoanProvider {

    Loan process(final String code);
}
