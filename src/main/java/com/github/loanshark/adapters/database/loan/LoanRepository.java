package com.github.loanshark.adapters.database.loan;

import com.github.loanshark.adapters.database.data.LoanData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<LoanData, Long> {

    @Query("select l from LoanData l join fetch l.customer where l.code = :code")
    Optional<LoanData> findLoan(@Param("code") final String code);
}
