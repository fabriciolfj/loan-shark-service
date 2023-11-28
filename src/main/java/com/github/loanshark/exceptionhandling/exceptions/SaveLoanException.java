package com.github.loanshark.exceptionhandling.exceptions;

import static com.github.loanshark.exceptionhandling.enums.ErrorEnums.SAVE_LOAN_ERROR;

public class SaveLoanException extends RuntimeException {

    public SaveLoanException() {
        super(SAVE_LOAN_ERROR.getMessage());
    }
}
