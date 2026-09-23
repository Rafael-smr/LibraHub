package com.libra.smr.controller;

import com.libra.smr.dto.MemberRequestDto;
import com.libra.smr.dto.MemberResponseDto;
import com.libra.smr.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;

    public  MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<MemberResponseDto> findAllMembers() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public MemberResponseDto findMemberById(@PathVariable long id) {
        return memberService.findById(id);
    }

    @PostMapping
    public MemberResponseDto createMember(@Valid @RequestBody MemberRequestDto memberRequestDto) {
        return memberService.createMember(memberRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable long id) {
        memberService.deleteMember(id);
    }

    @PutMapping("/{id}")
    public MemberResponseDto updateMember(@PathVariable long id, @Valid @RequestBody MemberRequestDto memberRequestDto) {
        return memberService.updateMember(id, memberRequestDto);
    }
}
