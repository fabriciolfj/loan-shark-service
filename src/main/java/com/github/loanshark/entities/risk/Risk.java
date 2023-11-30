package com.github.loanshark.entities.risk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Builder
@Getter
@AllArgsConstructor
public class Risk {

    private LoanVO loan;
    private CustomerVO customer;
    private BigDecimal committedValue;
    private Optional<LocalDate> dateLastLoan;
    private int score;

    public void setCustomer(final CustomerVO vo) {
        this.customer = vo;
    }

    public void setLoan(final LoanVO vo) {
        this.loan = vo;
    }

    public String getCodeLoan() {
        return this.loan.code();
    }
}
