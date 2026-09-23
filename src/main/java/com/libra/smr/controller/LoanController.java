package com.libra.smr.controller;

import com.libra.smr.dto.LoanRequestDto;
import com.libra.smr.dto.LoanResponseDto;
import com.libra.smr.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<LoanResponseDto> getLoans(){
        return loanService.getAllLoan();
    }

    @GetMapping("/{id}")
    public LoanResponseDto getLoanById(@PathVariable Long id){
        return loanService.getLoanById(id);
    }

    @PostMapping
    public LoanResponseDto createLoan(@Valid @RequestBody LoanRequestDto loanRequestDto){
        return loanService.createLoan(loanRequestDto);
    }

    @PostMapping("/{id}/renew")
    public LoanResponseDto renewLoan(@PathVariable Long id) {
        return loanService.renewLoan(id);
    }

    @PostMapping("/{id}/return")
    public LoanResponseDto returnLoan(@PathVariable Long id) {
        return loanService.returnLoan(id);
    }
}
