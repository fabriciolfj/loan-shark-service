package com.github.loanshark.entities.loan;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Loan {

    @EqualsAndHashCode.Include
    private String code;
    private final CustomerVO customer;
    private final DetailsVO details;
    private StatusLoanVO status;
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

    public LocalDate getBirthdayCustomer() {
        return this.customer.birthday();
    }

    public String getDocumentCustomer() {
        return this.customer.document();
    }

    public LocalDateTime getRequestDate() {
        return this.details.requestDate();
    }

    public String getName() {
        return this.customer.name();
    }

    public BigDecimal getValue() {
        return this.details.value();
    }

    public Loan approved() {
        this.status = StatusLoanVO.APPROVED;
        return this;
    }

    public Loan disapproved() {
        this.status = StatusLoanVO.DISAPPROVED;
        return this;
    }

}
