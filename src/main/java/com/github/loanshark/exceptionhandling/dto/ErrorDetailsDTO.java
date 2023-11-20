package com.github.loanshark.exceptionhandling.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorDetailsDTO {

    private String field;
    private String message;
}
