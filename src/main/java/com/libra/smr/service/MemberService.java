package com.libra.smr.service;

import com.libra.smr.dto.MemberRequestDto;
import com.libra.smr.dto.MemberResponseDto;
import com.libra.smr.entity.Member;
import com.libra.smr.exception.MemberNotFoundException;
import com.libra.smr.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponseDto> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponseDto::new)
                .toList();
    }

    public MemberResponseDto findById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));

        return new MemberResponseDto(member);
    }

    public MemberResponseDto createMember(MemberRequestDto memberRequestDto) {
        Member member = new Member();

        member.setName(memberRequestDto.name());

        Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(savedMember);
    }

    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));

        memberRepository.delete(member);
    }

    public  MemberResponseDto updateMember(Long id, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));

        member.setName(memberRequestDto.name());

        Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(savedMember);
    }
}
