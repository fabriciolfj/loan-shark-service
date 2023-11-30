package com.github.loanshark.entities.loan;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.util.stream.Stream.of;

@RequiredArgsConstructor
@Getter
public enum StatusLoanVO {

    APPROVED,
    DISAPPROVED,
    PENDING,
    CANCELED;

    private String describe;

    public static StatusLoanVO toEnum(final String value) {
        return of(StatusLoanVO.values())
                .filter(c -> c.describe.equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }
}
