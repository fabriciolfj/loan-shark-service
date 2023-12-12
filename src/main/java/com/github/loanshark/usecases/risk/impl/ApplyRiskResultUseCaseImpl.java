package com.github.loanshark.usecases.risk.impl;

import com.github.loanshark.annotations.UseCase;
import com.github.loanshark.configuration.ScoreRiskConfigurationProperties;
import com.github.loanshark.entities.risk.Risk;
import com.github.loanshark.usecases.risk.ApplyRiskResultUseCase;
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

    @Override
    @Transactional(propagation = Propagation.REQUIRED, transactionManager = "kafkaTransactionManager")
    public void execute(final Risk risk) {
        var method = log.event().m("applyRiskResult").param("loan", risk.getLoan());
        if (properties.getApproved() >= risk.getScore()) {
            method.info("approved");

            provider.process(risk.approved());
            return;
        }

        provider.process(risk.disapproved());
        method.info("disapproved");
    }
}
