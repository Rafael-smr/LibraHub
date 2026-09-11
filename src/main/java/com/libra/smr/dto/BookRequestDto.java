package com.libra.smr.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookRequestDto(

        @NotBlank
        @Size(max = 150)
        String title,

        @NotBlank
        @Size(max = 100)
        String author
) {}