package com.github.loanshark.usecases;

import java.math.BigDecimal;

public class Constants {

    private Constants() { }

    public static final int AGE_INIT = 21;
    public static final BigDecimal PERCENT_COMMITTED_LIMIT =  BigDecimal.valueOf(30);
    public static final BigDecimal VALUE_100 = BigDecimal.valueOf(100);
    public static final int DAYS_LIMIT = 30;
    public static final int DAYS_DEFAULT = 1000;
}