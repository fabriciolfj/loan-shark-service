package com.github.loanshark.adapters.database.loan;

import com.github.loanshark.adapters.database.customer.CustomerDatabase;
import com.github.loanshark.entities.loan.Loan;
import com.github.loanshark.entities.risk.Risk;
import com.github.loanshark.exceptionhandling.exceptions.LoanNotFoundException;
import com.github.loanshark.exceptionhandling.exceptions.SaveLoanException;
import com.github.loanshark.usecases.loan.providers.FindLoanProvider;
import com.github.loanshark.usecases.loan.providers.SaveLoanProvider;
import com.github.loanshark.usecases.risk.providers.FetchLoanDataProvider;
import com.github.loanshark.util.EventLogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.github.loanshark.adapters.database.loan.LoanDataMapper.toData;

@RequiredArgsConstructor
@Component
public class LoanPersistProviderImpl implements SaveLoanProvider, FetchLoanDataProvider, FindLoanProvider {

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
            log.event().m("process").action("save error")
                    .param("loan", loan.getCode())
                    .param("fail", e.getMessage())
                    .error();

            throw new SaveLoanException();
        }
    }

    @Override
    public Loan process(final Risk risk) {
        final var data = loanRepository.findLoan(risk.getCodeLoan());

        if (data.isEmpty()) {
            log.event()
                    .m("findLoan")
                    .param("findLoan", risk.getCodeLoan())
                    .param("message", "not found")
                    .error();

            throw new LoanNotFoundException();
        }

        return LoanDataMapper.toEntity(data.get());
    }

    @Override
    public Loan process(final String code) {
        final var data = loanRepository.findLoan(code);

        if (data.isEmpty()) {
            log.event()
                    .m("findLoan")
                    .param("findLoan", code)
                    .param("message", "not found")
                    .error();

            throw new LoanNotFoundException();
        }

        return LoanDataMapper.toEntity(data.get());
    }
}
