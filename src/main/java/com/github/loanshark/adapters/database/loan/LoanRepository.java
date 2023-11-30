package com.github.loanshark.adapters.database.loan;

import com.github.loanshark.adapters.database.data.LoanData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<LoanData, Long> {

    Optional<LoanData> findByCode(final String code);
}
