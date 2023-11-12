package com.gifthub.loanshark.entities;

import java.time.LocalDate;

public record CustomerEntity(String name, String document, LocalDate birthday) {
}
