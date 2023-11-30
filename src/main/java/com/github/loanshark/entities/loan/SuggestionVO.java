package com.github.loanshark.entities.loan;


import java.math.BigDecimal;

record SuggestionVO(BigDecimal fees, int installments, BigDecimal endValue, StatusSuggestionVO status) { }
