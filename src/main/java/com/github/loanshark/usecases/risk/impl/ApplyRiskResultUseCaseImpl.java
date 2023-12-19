package com.github.loanshark.usecases.risk.impl;

import com.github.loanshark.annotations.UseCase;
import com.github.loanshark.configuration.ScoreRiskConfigurationProperties;
import com.github.loanshark.entities.risk.Risk;
import com.github.loanshark.usecases.risk.ApplyRiskResultUseCase;
import com.github.loanshark.usecases.risk.NotifyApplyRiskCompletedUseCase;
import com.github.loanshark.usecases.risk.providers.PersistRiskProvider;
import com.github.loanshark.util.EventLogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class ApplyRiskResultUseCaseImpl implements ApplyRiskResultUseCase {

    private static final EventLogUtil log = EventLogUtil.defaults(ApplyRiskResultUseCaseImpl.class);

    private final ScoreRiskConfigurationProperties properties;
    private final PersistRiskProvider provider;
    private final NotifyApplyRiskCompletedUseCase notifyApplyRiskCompletedUseCase;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, transactionManager = "kafkaTransactionManager")
    public void execute(final Risk risk) {
        var result = risk.evaluate(properties.getApproved());
        log.event().m("applyRiskResult").param("loan", risk.getLoan())
                .param("status", result.getStatusDescribe()).info();

        provider.process(risk);
        notifyApplyRiskCompletedUseCase.execute(risk);
    }
}
