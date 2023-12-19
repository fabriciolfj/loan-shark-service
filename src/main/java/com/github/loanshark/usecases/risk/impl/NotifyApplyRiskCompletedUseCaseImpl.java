package com.github.loanshark.usecases.risk.impl;

import com.github.loanshark.annotations.UseCase;
import com.github.loanshark.entities.risk.Risk;
import com.github.loanshark.usecases.risk.NotifyApplyRiskCompletedUseCase;
import com.github.loanshark.usecases.risk.providers.NotifyRiskProvider;
import com.github.loanshark.util.EventLogUtil;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class NotifyApplyRiskCompletedUseCaseImpl implements NotifyApplyRiskCompletedUseCase {

    private final EventLogUtil log = EventLogUtil.defaults(NotifyApplyRiskCompletedUseCaseImpl.class);

    private final NotifyRiskProvider notifyRiskProvider;

    @Override
    public void execute(final Risk risk) {
        notifyRiskProvider.process(risk.getCodeLoan());

        log.event()
                .m("notifyApplyRiskCompleted")
                .param("loan", risk.getCodeLoan())
                .info("executed");
    }
}
