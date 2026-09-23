package com.libra.smr.dto;

import com.libra.smr.entity.Member;

public record MemberResponseDto(
        Long id,
        String name
) {
    public MemberResponseDto(Member member) {
        this(
                member.getId(),
                member.getName()
        );
    }
}