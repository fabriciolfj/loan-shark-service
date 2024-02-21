package com.github.loanshark.entrypoints.listeners.initprocess;

import com.github.loanshark.entities.risk.LoanVO;
import com.github.loanshark.entities.risk.Risk;

public class RiskMapper {

    private RiskMapper() { }

    public static Risk toRisk(final LoanIdDTO dto) {
        final var loan = new LoanVO(dto.code(), null, null);
        return Risk.builder()
                .loan(loan)
                .build();
    }
}
