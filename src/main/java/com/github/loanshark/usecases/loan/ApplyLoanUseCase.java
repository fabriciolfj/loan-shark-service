package com.github.loanshark.usecases.loan;

import com.github.loanshark.entities.loan.Loan;

public interface ApplyLoanUseCase {

    void execute(final Loan loan);
}
