package com.gifthub.loanshark.entities;


public record LoanEntity(CustomerEntity customerEntity, RiskEntity riskEntity, DetailsLoanEntity details) {

}
