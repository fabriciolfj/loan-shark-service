package com.github.loanshark.adapters.database;

import com.github.loanshark.adapters.database.customer.CustomerDatabase;
import com.github.loanshark.adapters.database.loan.LoanRepository;
import com.github.loanshark.entities.loan.Loan;
import com.github.loanshark.usecases.loan.SaveLoanProvider;
import com.github.loanshark.util.EventLogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.github.loanshark.adapters.database.loan.LoanDataMapper.toData;

@RequiredArgsConstructor
@Component
public class LoanPersistProviderImpl implements SaveLoanProvider {

    private static final EventLogUtil log = EventLogUtil.defaults(LoanPersistProviderImpl.class);

    private final CustomerDatabase customerDatabase;
    private final LoanRepository loanRepository;

    @Override
    public void process(final Loan loan) {
        try {
            var customer = customerDatabase.findOrCreate(loan);
            loanRepository.save(toData(loan, customer));

            log.event().m("process").action("save success")
                    .param("loan", loan.getCode())
                    .info();
        } catch (Exception e) {
            log.event().m("process").action("save success")
                    .param("loan", loan.getCode())
                    .param("fail", e.getMessage())
                    .info();

            throw new RuntimeException(e.getMessage());
        }
    }
}
