package com.github.loanshark.exceptionhandling.exceptions;


import static com.github.loanshark.exceptionhandling.enums.ErrorEnums.LOAN_NOT_FOUND;

public class LoanNotFoundException extends RuntimeException {

    public LoanNotFoundException() {
        super(LOAN_NOT_FOUND.getMessage());
    }
}
