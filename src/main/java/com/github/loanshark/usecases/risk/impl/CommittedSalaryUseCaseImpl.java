package com.github.loanshark.usecases.risk.impl;

import com.github.loanshark.annotations.UseCase;
import com.github.loanshark.entities.risk.Risk;
import com.github.loanshark.usecases.risk.ProcessAnalyseRiskUseCase;
import com.github.loanshark.usecases.risk.providers.GetDetailsCustomerProvider;
import com.github.loanshark.util.EventLogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

import java.math.BigDecimal;

import static com.github.loanshark.usecases.Constants.PERCENT_COMMITTED_LIMIT;

@UseCase
@Order(2)
@RequiredArgsConstructor
public class CommittedSalaryUseCaseImpl implements ProcessAnalyseRiskUseCase {

    private static final EventLogUtil log = EventLogUtil.defaults(CommittedSalaryUseCaseImpl.class);

    private final GetDetailsCustomerProvider provider;

    @Value("${riskScore.salary}")
    private int value;

    @Override
    public Risk execute(final Risk risk) {
        final var result = provider.process(risk);
        final var committed = result.calculeSalaryCommitted();

        switch (committed.compareTo(PERCENT_COMMITTED_LIMIT)) {
            case 1 -> result.addScore(value - 10);
            default -> result.addScore(value);
        }

        log.event()
                .m("committedSalaryUseCase")
                .param("apply score loan", risk.getLoan())
                .info();
        return risk;
    }
}
