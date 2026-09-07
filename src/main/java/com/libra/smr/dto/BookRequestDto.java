package com.libra.smr.dto;

import com.libra.smr.entity.Book;
import jakarta.validation.constraints.NotBlank;

public record BookRequestDto(
        @NotBlank
        String title,

        @NotBlank
        String author
) {}
