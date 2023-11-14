package com.gifthub.loanshark.entities.loan;


import com.gifthub.loanshark.entities.loan.vo.CustomerEntity;
import com.gifthub.loanshark.entities.loan.vo.DetailsLoanEntity;
import com.gifthub.loanshark.entities.loan.vo.StatusLoanEntity;

public class LoanEntity {

    private CustomerEntity customerEntity;
    private DetailsLoanEntity details;
    private StatusLoanEntity status;

    public LoanEntity(final CustomerEntity customer, final DetailsLoanEntity details) {
        this.customerEntity = customer;
        this.details = details;
        this.status = StatusLoanEntity.PENDING;
    }

}
