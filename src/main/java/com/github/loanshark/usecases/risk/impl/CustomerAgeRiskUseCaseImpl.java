package com.github.loanshark.usecases.risk.impl;

import com.github.loanshark.annotations.UseCase;
import com.github.loanshark.entities.risk.Risk;
import com.github.loanshark.usecases.risk.ProcessAnalyseRiskUseCase;
import com.github.loanshark.util.EventLogUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

import static com.github.loanshark.usecases.Constants.AGE_INIT;

@UseCase
@Order(1)
public class CustomerAgeRiskUseCaseImpl implements ProcessAnalyseRiskUseCase {

    private static final EventLogUtil log = EventLogUtil.defaults(CommittedSalaryUseCaseImpl.class);

    @Value("${riskScore.age}")
    private int value;

    @Override
    public Risk execute(final Risk risk) {
        if (risk.getAge() >= AGE_INIT) {
            risk.addScore(value);
        }

        log.event()
                .m("customerAgeRisk")
                .param("apply score loan", risk.getLoan())
                .info();
        return risk;
    }
}
