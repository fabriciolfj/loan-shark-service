package com.github.loanshark.entrypoints.controller;

import com.github.loanshark.entrypoints.controller.dto.LoanRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/loans")
public class LoanController {


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "${api.loan-controller.description}"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest}"),
            @ApiResponse(responseCode = "201", description = "${api.response-codes.creation}")
    })
    public void requestLoan(@RequestBody @Valid final LoanRequest loanRequest) {

    }
}
