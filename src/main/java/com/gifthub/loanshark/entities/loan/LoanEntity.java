package com.gifthub.loanshark.entities.loan;


import com.gifthub.loanshark.entities.loan.vo.CustomerEntity;
import com.gifthub.loanshark.entities.loan.vo.DetailsLoanEntity;
import com.gifthub.loanshark.entities.loan.vo.StatusLoanEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LoanEntity {

    @EqualsAndHashCode.Include
    private String code;
    private CustomerEntity customerEntity;
    private DetailsLoanEntity details;
    private StatusLoanEntity status;

    public LoanEntity(final CustomerEntity customer, final DetailsLoanEntity details) {
        this.customerEntity = customer;
        this.details = details;
        this.status = StatusLoanEntity.PENDING;
        this.code = UUID.randomUUID().toString();
    }

    public BigDecimal getSalary() {
        return customerEntity.salary();
    }

}
