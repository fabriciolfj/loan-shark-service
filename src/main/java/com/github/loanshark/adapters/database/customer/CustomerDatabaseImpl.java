package com.github.loanshark.adapters.database.customer;

import com.github.loanshark.adapters.database.data.CustomerData;
import com.github.loanshark.entities.loan.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.github.loanshark.adapters.database.customer.CustomerDataMapper.toData;

@Component
@RequiredArgsConstructor
public class CustomerDatabaseImpl implements CustomerDatabase {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerData findOrCreateCustomer(final Loan loan) {
        var customer = customerRepository.findByDocument(loan.getDocumentCustomer());

        return customer.orElseGet(() -> customerRepository.save(toData(loan)));
    }
}
