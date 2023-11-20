package com.github.loanshark.exceptionhandling.enums;

import java.util.ResourceBundle;

public enum ErrorEnums {

    SALARY_INVALID;

    public String getMessage() {
        var bundle = ResourceBundle.getBundle("exceptions/message");
        return bundle.getString(this.name() + ".message");
    }
}
