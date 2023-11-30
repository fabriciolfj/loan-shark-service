package com.github.loanshark.adapters.database.loan;

import com.github.loanshark.adapters.database.data.CustomerData;
import com.github.loanshark.adapters.database.data.LoanData;
import com.github.loanshark.entities.loan.CustomerVO;
import com.github.loanshark.entities.loan.DetailsVO;
import com.github.loanshark.entities.loan.Loan;
import com.github.loanshark.entities.loan.StatusLoanVO;
import java.util.Collections;

public class LoanDataMapper {

    private LoanDataMapper() { }

    public static LoanData toData(final Loan loan, final CustomerData customerData) {
        return LoanData
                .builder()
                .code(loan.getCode())
                .status(loan.getStatus().getDescribe())
                .value(loan.getValue())
                .customer(customerData)
                .requestDate(loan.getRequestDate())
                .build();
    }

    public static Loan toEntity(final LoanData data) {
        return Loan
                .builder()
                .code(data.getCode())
                .suggestions(Collections.emptyList())
                .customer(new CustomerVO(data.getNameCustomer(), data.getDocument(), data.getBirthday(), data.getSalary()))
                .details(new DetailsVO(data.getValue(), data.getRequestDate()))
                .status(StatusLoanVO.toEnum(data.getStatus()))
                .build();
    }
}
