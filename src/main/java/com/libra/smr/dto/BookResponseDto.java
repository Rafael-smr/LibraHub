package com.libra.smr.dto;

import com.libra.smr.entity.Book;
import com.libra.smr.entity.enums.BookStatus;

public record BookResponseDto(
        Long id,
        String title,
        String author,
        BookStatus status
) {
    public BookResponseDto(Book book) {
        this(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getStatus()
        );
    }
}
