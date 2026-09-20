package com.libra.smr.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record MemberRequestDto(

        @NotBlank
        @Size(max = 150)
        String name,

        @NotNull
        @FutureOrPresent
        LocalDate loanDate,

        @NotNull
        LocalDate returnDate,

        @NotNull
        Long bookId

) {
}