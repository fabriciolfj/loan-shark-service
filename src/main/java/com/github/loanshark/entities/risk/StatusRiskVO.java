package com.github.loanshark.entities.risk;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
enum StatusRiskVO {

    APPROVED("approved"),
    FAILED("failed");

    private final String describe;

    public StatusRiskVO toStatus(final String describe) {
        return Stream.of(StatusRiskVO.values())
                .filter(v -> v.getDescribe().equalsIgnoreCase(describe))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
