package com.github.loanshark.adapters.database.risk;

import com.github.loanshark.adapters.database.data.RiskData;
import com.github.loanshark.entities.risk.Risk;

import java.time.LocalDateTime;

public class RiskDataMpper {

    private RiskDataMpper() { }

    public static RiskData toData(final Risk risk) {
        return RiskData
                .builder()
                .loan(risk.getCodeLoan())
                .committedValue(risk.getCommittedValue())
                .status(risk.getStatusDescribe())
                .lastDate(LocalDateTime.now())
                .build();
    }
}
