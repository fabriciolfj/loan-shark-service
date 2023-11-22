package com.github.loanshark.entrypoints.controller.loan;

import com.github.loanshark.entrypoints.controller.loan.dto.LoanRequest;
import com.github.loanshark.usecases.loan.ApplyLoanUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.github.loanshark.entrypoints.controller.loan.LoanDTOMapper.toEntity;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/loans")
public class LoanController {

    private final ApplyLoanUseCase applyLoanUseCase;


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
        var loan = toEntity(loanRequest);

        applyLoanUseCase.execute(loan);
    }
}
