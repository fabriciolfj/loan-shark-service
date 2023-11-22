package com.github.loanshark.adapters.database.customer;

import com.github.loanshark.adapters.database.data.CustomerData;
import com.github.loanshark.entities.loan.Loan;

public interface CustomerDatabase {

    CustomerData findOrCreateCustomer(final Loan loan);
}
