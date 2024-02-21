package com.github.loanshark.entrypoints.listeners.riskcompleted;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotifyRiskDTO {

    private String loan;
    private String status;
}
