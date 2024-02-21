package com.github.loanshark.usecases.loan;

public interface ProcessStatusLoanUseCase {

    void execute(final String loan, final String status);
}
