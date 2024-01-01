package com.github.loanshark.usecases.loan.impl;

import com.github.loanshark.annotations.UseCase;
import com.github.loanshark.entities.loan.Loan;
import com.github.loanshark.usecases.loan.ApplyLoanUseCase;
import com.github.loanshark.usecases.loan.providers.SendProcessLoanProvider;
import com.github.loanshark.usecases.loan.providers.SaveLoanProvider;
import com.github.loanshark.util.EventLogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@UseCase
@RequiredArgsConstructor
public class ApplyLoanUseCaseImpl implements ApplyLoanUseCase {

    private static final EventLogUtil log = EventLogUtil.defaults(ApplyLoanUseCaseImpl.class);

    private final SendProcessLoanProvider providerProcess;
    private final SaveLoanProvider saveLoanProvider;

    @Override
    @Transactional(transactionManager = "kafkaTransactionManager", propagation = Propagation.REQUIRED)
    public void execute(final Loan loan) {
        log.event().m("execute")
                .param("loan", loan.getCode())
                .action("before init process")
                .info();

        saveLoanProvider.process(loan);
        providerProcess.process(loan);

        log.event().m("execute")
                .param("loan", loan.getCode())
                .action("end process")
                .info();
    }
}
