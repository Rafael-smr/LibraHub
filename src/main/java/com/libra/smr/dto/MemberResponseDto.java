package com.libra.smr.dto;

import com.libra.smr.entity.Book;
import com.libra.smr.entity.Member;

import java.time.LocalDate;

public record MemberResponseDto(
        Long id,
        String name,
        LocalDate loanDate,
        LocalDate returnDate,
        Book book
) {
    public MemberResponseDto(Member member){
        this(
                member.getId(),
                member.getName(),
                member.getLoanDate(),
                member.getReturnDate(),
                member.getBook());
    }
}
