package com.github.loanshark.adapters.database.loan;

import com.github.loanshark.adapters.database.loan.data.CustomerData;
import com.github.loanshark.adapters.database.loan.data.LoanData;
import com.github.loanshark.entities.loan.Loan;

import java.math.BigDecimal;

public class LoanDataMapper {

    private LoanDataMapper() { }

    public static LoanData toData(final Loan loan, final CustomerData customerData) {
        return LoanData
                .builder()
                .code(loan.getCode())
                .status(loan.getStatus().name())
                .endValue(BigDecimal.ZERO)
                .customer(customerData)
                .requestDate(loan.getRequestDate())
                .build();
    }
}
