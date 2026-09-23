package com.libra.smr.dto;

import com.libra.smr.entity.Loan;

import java.time.LocalDate;

public record LoanResponseDto(
        Long id,
        LocalDate loanDate,
        LocalDate returnDate,
        Long bookId,
        Long memberId
) {
    public LoanResponseDto(Loan loan) {
        this(
                loan.getId(),
                loan.getLoanDate(),
                loan.getReturnDate(),
                loan.getBook().getId(),
                loan.getMember().getId()
        );
    }
}
