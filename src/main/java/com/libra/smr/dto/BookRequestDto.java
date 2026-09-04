package com.libra.smr.dto;

import com.libra.smr.enums.BookStatus;

public record BookRequestDto(
        String title,
        String author
) {
}
