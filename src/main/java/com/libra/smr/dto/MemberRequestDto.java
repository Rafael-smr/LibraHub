package com.libra.smr.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemberRequestDto(

        @NotBlank
        @Size(max = 150)
        String name

) {
}