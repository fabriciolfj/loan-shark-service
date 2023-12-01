package com.github.loanshark.usecases.risk.mapper;

import com.github.loanshark.entities.loan.Loan;
import com.github.loanshark.entities.risk.CustomerVO;
import com.github.loanshark.entities.risk.LoanVO;
import com.github.loanshark.entities.risk.Risk;

public class EnrichRiskWithLoanMapper {

    private EnrichRiskWithLoanMapper() { }

    public static Risk toRisk(final Loan loan, final Risk risk) {
        final var customerVO = new CustomerVO(loan.getBirthdayCustomer(), loan.getSalary());
        final var loanVO = new LoanVO(loan.getCode(), loan.getValue(), loan.getRequestDate());

        risk.setCustomer(customerVO);
        risk.setLoan(loanVO);

        return risk;
    }
}
