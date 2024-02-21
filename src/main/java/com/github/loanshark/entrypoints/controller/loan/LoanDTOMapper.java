package com.github.loanshark.entrypoints.controller.loan;

import com.github.loanshark.entities.loan.CustomerVO;
import com.github.loanshark.entities.loan.DetailsVO;
import com.github.loanshark.entities.loan.Loan;
import com.github.loanshark.entities.loan.StatusLoanVO;
import com.github.loanshark.entrypoints.controller.loan.dto.LoanRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.github.loanshark.util.StringToLocalDateUtil.toLocalDate;

public class LoanDTOMapper {

    private LoanDTOMapper() { }

    static Loan toEntity(final LoanRequest request) {
        var customer = new CustomerVO(
                request.name(),
                request.document(), toLocalDate(request.birthday()),
                request.salary());

        var details = new DetailsVO(request.loan(), LocalDateTime.now());
        return new Loan(customer, details);
    }
}
