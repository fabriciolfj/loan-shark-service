package com.github.loanshark.adapters.database.loan;

import com.github.loanshark.adapters.database.loan.data.CustomerData;
import com.github.loanshark.entities.loan.Loan;

public interface CustomerDatabase {

    CustomerData findOrCreateCustomer(final Loan loan);
}
