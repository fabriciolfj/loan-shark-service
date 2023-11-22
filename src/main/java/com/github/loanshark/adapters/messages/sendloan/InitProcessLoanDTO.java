package com.github.loanshark.adapters.messages.sendloan;


import com.github.loanshark.entities.loan.Loan;

public record InitProcessLoanDTO(String code) {

    public static InitProcessLoanDTO toDto(final Loan loan) {
        return new InitProcessLoanDTO(loan.getCode());
    }
}
