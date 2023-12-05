package com.github.loanshark.usecases.risk.impl;

import com.github.loanshark.annotations.UseCase;
import com.github.loanshark.entities.risk.Risk;
import com.github.loanshark.usecases.risk.ProcessAnalyseRiskUseCase;
import com.github.loanshark.util.EventLogUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

import static com.github.loanshark.usecases.Constants.DAYS_LIMIT;

@UseCase
@Order(3)
public class DateLastLoanUseCaseImpl implements ProcessAnalyseRiskUseCase {

    private static final EventLogUtil log = EventLogUtil.defaults(DateLastLoanUseCaseImpl.class);

    @Value("${riskScore.daysValid}")
    public int value;

    @Override
    public Risk execute(final Risk risk) {
        var days = risk.getQtdDaysLastLoan();
        if (days >= DAYS_LIMIT) {
            risk.addScore(value);
        }

        log.event()
                .m("dateLastLoanUseCase")
                .param("apply score loan", risk.getLoan())
                .info();
        return risk;
    }
}
