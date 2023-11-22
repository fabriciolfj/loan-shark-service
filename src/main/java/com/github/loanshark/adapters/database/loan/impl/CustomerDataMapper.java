package com.github.loanshark.adapters.database.loan.impl;

import com.github.loanshark.adapters.database.loan.data.CustomerData;
import com.github.loanshark.entities.loan.Loan;

public class CustomerDataMapper {

    private CustomerDataMapper() {

    }

    public static CustomerData toData(final Loan loan) {
        return CustomerData
                .builder()
                .birthday(loan.getBirthdayCustomer())
                .document(loan.getDocumentCustomer())
                .salary(loan.getSalary())
                .build();
    }
}
