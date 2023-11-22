package com.github.loanshark.adapters.database.loan.repositories;

import com.github.loanshark.adapters.database.loan.data.LoanData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanData, Long> {
}
