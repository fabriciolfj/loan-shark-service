package com.github.loanshark.adapters.database.loan;

import com.github.loanshark.adapters.database.loan.repositories.LoanRepository;
import com.github.loanshark.entities.loan.Loan;
import com.github.loanshark.usecases.loan.SaveLoanProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.github.loanshark.adapters.database.loan.LoanDataMapper.toData;

@RequiredArgsConstructor
@Slf4j
@Component
public class LoanPersistProviderImpl implements SaveLoanProvider {

    private final CustomerDatabase customerDatabase;
    private final LoanRepository loanRepository;

    @Override
    public void process(final Loan loan) {
        try {
            var customer = customerDatabase.findOrCreateCustomer(loan);
            loanRepository.save(toData(loan, customer));

            log.info("");
        } catch (Exception e) {
            log.error("");
            throw new RuntimeException(e.getMessage());
        }
    }
}
