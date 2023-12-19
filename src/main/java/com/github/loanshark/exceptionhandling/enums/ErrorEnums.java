package com.github.loanshark.exceptionhandling.enums;

import java.util.ResourceBundle;

public enum ErrorEnums {

    SAVE_LOAN_ERROR,
    LOAN_NOT_FOUND;

    public String getMessage() {
        var bundle = ResourceBundle.getBundle("exceptions/message");
        return bundle.getString(this.name() + ".message");
    }
}
