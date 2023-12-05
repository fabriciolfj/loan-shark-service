package com.github.loanshark.adapters.client.bureau;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record CustomerBereauDTO(String dateRequest, @JsonProperty("others_loan") BigDecimal othersLoan) {
}