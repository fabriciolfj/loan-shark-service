package com.github.loanshark.usecases.risk.impl;

import com.github.loanshark.annotations.UseCase;
import com.github.loanshark.entities.risk.Risk;
import com.github.loanshark.usecases.risk.EnrichRiskUseCase;
import com.github.loanshark.usecases.risk.providers.FetchLoanDataProvider;
import com.github.loanshark.util.EventLogUtil;
import lombok.RequiredArgsConstructor;

import static com.github.loanshark.usecases.risk.impl.EnrichRiskWithLoanMapper.toRisk;

@UseCase
@RequiredArgsConstructor
public class EnrichRiskUseCaseImpl implements EnrichRiskUseCase {

    private static final EventLogUtil log = EventLogUtil.defaults(EnrichRiskUseCaseImpl.class);
    private final FetchLoanDataProvider provider;

    @Override
    public Risk execute(final Risk risk) {
        final var loan = provider.process(risk);
        final var riskEnrich = toRisk(loan, risk);

        log.event()
                .m("enrich risk")
                .param("loan", risk.getCodeLoan())
                .info();
        return riskEnrich;
    }
}
