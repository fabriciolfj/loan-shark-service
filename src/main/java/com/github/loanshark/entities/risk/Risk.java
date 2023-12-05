package com.github.loanshark.entities.risk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import static com.github.loanshark.usecases.Constants.DAYS_DEFAULT;
import static com.github.loanshark.usecases.Constants.VALUE_100;

@Builder
@Getter
@AllArgsConstructor
public class Risk {

    private LoanVO loan;
    private CustomerVO customer;
    private BigDecimal committedValue;
    private LastLoanVO lastLoanVO;
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

    public int getAge() {
        var period = Period.between(this.customer.birthdayCustomer(), LocalDate.now());
        return period.getYears();
    }

    public void addScore(final int value) {
        this.score += value;
    }

    public String getDocumentCustomer() {
        return this.customer.document();
    }

    public Risk setLastLoan(final LastLoanVO vo) {
        this.lastLoanVO = vo;
        return this;
    }

    public BigDecimal calculeSalaryCommitted() {
        if (Objects.isNull(this.lastLoanVO)) {
            return BigDecimal.ZERO;
        }

        var salary = this.customer.salaryCustomer();
        var committed = this.lastLoanVO.value();

        return committed.divide(salary).multiply(VALUE_100).setScale(4, RoundingMode.FLOOR);
    }

    public int getQtdDaysLastLoan() {
        if(Objects.isNull(this.lastLoanVO)) {
            return DAYS_DEFAULT;
        }

        final var period = Period.between(this.lastLoanVO.lastRequest(), LocalDate.now());
        return period.getDays();
    }
}
