package com.libra.smr.dto;

import jakarta.validation.constraints.NotNull;

public record LoanRequestDto(

        @NotNull
        Long bookId,
        @NotNull
        Long memberId
) {
}
