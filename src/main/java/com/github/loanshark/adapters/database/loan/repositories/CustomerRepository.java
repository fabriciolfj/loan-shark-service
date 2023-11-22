package com.github.loanshark.adapters.database.loan.repositories;

import com.github.loanshark.adapters.database.loan.data.CustomerData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerData, Long> {

    Optional<CustomerData> findByDocument(final String document);
}
