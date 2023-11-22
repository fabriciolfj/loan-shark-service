package com.github.loanshark.usecases.loan.impl;

import com.github.loanshark.annotations.UseCase;
import com.github.loanshark.entities.loan.Loan;
import com.github.loanshark.usecases.loan.ApplyLoanUseCase;
import com.github.loanshark.usecases.loan.ProcessLoanProvider;
import com.github.loanshark.usecases.loan.SaveLoanProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class ApplyLoanUseCaseImpl implements ApplyLoanUseCase {

    private final ProcessLoanProvider providerProcess;
    private final SaveLoanProvider saveLoanProvider;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void execute(final Loan loan) {
        log.info("process {}, param {}", "loan", loan.getCode());
        saveLoanProvider.process(loan);
        providerProcess.process(loan);

        log.info("finished process loan {}", loan.getCode());
    }
}
