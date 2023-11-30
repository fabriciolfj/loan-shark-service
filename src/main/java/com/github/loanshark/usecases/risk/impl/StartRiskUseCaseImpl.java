package com.github.loanshark.usecases.risk.impl;

import com.github.loanshark.annotations.UseCase;
import com.github.loanshark.entities.risk.Risk;
import com.github.loanshark.usecases.risk.ApplyRiskResultUseCase;
import com.github.loanshark.usecases.risk.ProcessAnalyseRiskUseCase;
import com.github.loanshark.usecases.risk.StartAnalyzeRiskUseCase;
import com.github.loanshark.usecases.risk.providers.FetchLoanDataProvider;
import com.github.loanshark.util.EventLogUtil;
import lombok.RequiredArgsConstructor;
import java.util.List;

import static com.github.loanshark.usecases.risk.impl.EnrichRiskWithLoanMapper.toRisk;

@UseCase
@RequiredArgsConstructor
public class StartRiskUseCaseImpl implements StartAnalyzeRiskUseCase {

    private static final EventLogUtil log = EventLogUtil.defaults(StartAnalyzeRiskUseCase.class);

    private final FetchLoanDataProvider provider;
    private final List<ProcessAnalyseRiskUseCase> processes;
    private final ApplyRiskResultUseCase applyRiskResultUseCase;

    @Override
    public void execute(final Risk risk) {
        final var loan = provider.process(risk);
        final var riskEnrich = toRisk(loan, risk);

        final var result = processes.stream().map(c -> c.execute(riskEnrich))
                .findAny();

        log.event()
                .m("startRiskUseCase")
                .param("loan", risk.getCodeLoan())
                .param("risk", "analyse executed")
                .info();

        applyRiskResultUseCase.execute(result.get());
    }
}
