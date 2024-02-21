package com.github.loanshark.adapters.database.risk;

import com.github.loanshark.entities.risk.Risk;
import com.github.loanshark.usecases.risk.providers.PersistRiskProvider;
import com.github.loanshark.util.EventLogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersistRiskProviderImpl implements PersistRiskProvider {

    private static final EventLogUtil log = EventLogUtil.defaults(PersistRiskProviderImpl.class);
    private final RiskRepository repository;

    @Override
    public Risk process(final Risk risk) {
        var data = RiskDataMpper.toData(risk);
        repository.save(data);

        log.event()
                .m("persistRiskProviderImpl")
                .param("loan", risk.getLoan())
                .info();
        return risk;
    }
}
