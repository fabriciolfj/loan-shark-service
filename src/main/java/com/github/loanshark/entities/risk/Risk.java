package com.github.loanshark.entities.risk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
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
    private LastLoanVO lastLoan;
    private StatusRiskVO statusRisk;
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
        this.lastLoan = vo;
        return this;
    }

    public BigDecimal calculeSalaryCommitted() {
        if (Objects.isNull(this.lastLoan)) {
            return BigDecimal.ZERO;
        }

        var salary = this.customer.salaryCustomer();
        var committed = this.lastLoan.value();

        return committed.divide(salary, 4, RoundingMode.HALF_UP).multiply(VALUE_100);
    }

    public int getQtdDaysLastLoan() {
        if(Objects.isNull(this.lastLoan)) {
            return DAYS_DEFAULT;
        }

        final var period = Period.between(this.lastLoan.lastRequest(), LocalDate.now());
        return period.getDays();
    }

    public Risk approved() {
        this.statusRisk = StatusRiskVO.APPROVED;
        return this;
    }

    public Risk disapproved() {
        this.statusRisk = StatusRiskVO.FAILED;
        return this;
    }

    public String getStatusDescribe() {
        return this.statusRisk.getDescribe();
    }
}
