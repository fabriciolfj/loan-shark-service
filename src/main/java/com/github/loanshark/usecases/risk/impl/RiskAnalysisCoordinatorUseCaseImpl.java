package com.github.loanshark.usecases.risk.impl;

import com.github.loanshark.annotations.UseCase;
import com.github.loanshark.entities.risk.Risk;
import com.github.loanshark.usecases.risk.ApplyRiskResultUseCase;
import com.github.loanshark.usecases.risk.EnrichRiskUseCase;
import com.github.loanshark.usecases.risk.ProcessAnalyseRiskUseCase;
import com.github.loanshark.usecases.risk.RiskAnalysisCoordinatorUseCase;
import com.github.loanshark.util.EventLogUtil;
import lombok.RequiredArgsConstructor;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class RiskAnalysisCoordinatorUseCaseImpl implements RiskAnalysisCoordinatorUseCase {

    private static final EventLogUtil log = EventLogUtil.defaults(RiskAnalysisCoordinatorUseCase.class);

    private final EnrichRiskUseCase enrichRiskUseCase;
    private final List<ProcessAnalyseRiskUseCase> processes;
    private final ApplyRiskResultUseCase applyRiskResultUseCase;

    @Override
    public void execute(final Risk risk) {
        final var riskEnrich = enrichRiskUseCase.execute(risk);
        final var result = processes.stream().map(c -> c.execute(riskEnrich))
                        .toList().get(0);

        log.event()
                .m("startRiskUseCase")
                .param("loan", risk.getCodeLoan())
                .param("risk", "analyse executed")
                .info();

        applyRiskResultUseCase.execute(result);
    }
}
