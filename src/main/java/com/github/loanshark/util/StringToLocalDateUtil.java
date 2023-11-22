package com.github.loanshark.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateUtil {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private StringToLocalDateUtil() { }

    public static LocalDate toLocalDate(final String value) {
        return LocalDate.parse(value, DATE_FORMAT);
    }
}
