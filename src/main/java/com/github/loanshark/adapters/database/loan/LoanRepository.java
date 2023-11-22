package com.github.loanshark.adapters.database.loan;

import com.github.loanshark.adapters.database.data.LoanData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanData, Long> {
}
