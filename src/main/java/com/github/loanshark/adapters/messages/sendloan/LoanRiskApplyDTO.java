package com.github.loanshark.adapters.messages.sendloan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanRiskApplyDTO {

    private String loan;
    private String status;
}
