package com.gifthub.loanshark.entities.loan.vo;

import java.time.LocalDate;

public record CustomerEntity(String name, String document, LocalDate birthday) {
}
