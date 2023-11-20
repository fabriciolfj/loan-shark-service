package com.github.loanshark.entities.loan;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Loan {

    @EqualsAndHashCode.Include
    private String code;
    private final CustomerVO customer;
    private final DetailsVO details;
    private final StatusLoanVO status;
    private final List<SuggestionVO> suggestions;

    public Loan(final CustomerVO customer, final DetailsVO details) {
        this.customer = customer;
        this.details = details;
        this.status = StatusLoanVO.PENDING;
        this.code = UUID.randomUUID().toString();
        this.suggestions = Collections.emptyList();
    }

    public Loan(final String code, final CustomerVO customer, final DetailsVO details, final List<SuggestionVO> suggestions) {
        this.customer = customer;
        this.details = details;
        this.status = StatusLoanVO.PENDING;
        this.code = code;
        this.suggestions = suggestions;
    }

    public BigDecimal getSalary() {
        return customer.salary();
    }

}
