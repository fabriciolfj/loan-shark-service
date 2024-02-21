package com.github.loanshark.usecases.loan.impl;

import com.github.loanshark.annotations.UseCase;
import com.github.loanshark.entities.risk.Risk;
import com.github.loanshark.usecases.loan.providers.FindLoanProvider;
import com.github.loanshark.usecases.loan.ProcessStatusLoanUseCase;
import com.github.loanshark.usecases.loan.providers.SaveLoanProvider;
import com.github.loanshark.util.EventLogUtil;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ProcessStatusLoanUseCaseImpl implements ProcessStatusLoanUseCase {

    private static final EventLogUtil log = EventLogUtil.defaults(ProcessStatusLoanUseCaseImpl.class);

    private final FindLoanProvider findLoanProvider;
    private final SaveLoanProvider saveLoanProvider;

    @Override
    public void execute(final String codeLoan, final String status) {
        final var loan = findLoanProvider.process(codeLoan);
        var logMethod = log.event("processStatusLoanUseCase")
                .param("loan", loan)
                .param("status", status);

        if (Risk.isApproved(status)) {
            logMethod.info("approved");
            saveLoanProvider.process(loan.approved());
            return;
        }

        logMethod.info("disapproved");
        saveLoanProvider.process(loan.disapproved());
    }
}
