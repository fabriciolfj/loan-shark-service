package com.github.loanshark.exceptionhandling.enums;

import java.util.ResourceBundle;

public enum ErrorEnums {

    SAVE_LOAN_ERROR,
    SALARY_INVALID;

    public String getMessage() {
        var bundle = ResourceBundle.getBundle("exceptions/message");
        return bundle.getString(this.name() + ".message");
    }
}
