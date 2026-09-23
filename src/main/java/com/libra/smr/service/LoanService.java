package com.libra.smr.service;

import com.libra.smr.dto.LoanRequestDto;
import com.libra.smr.dto.LoanResponseDto;
import com.libra.smr.entity.Loan;
import com.libra.smr.entity.enums.BookStatus;
import com.libra.smr.exception.LoanNotFoundException;
import com.libra.smr.repository.BookRepository;
import com.libra.smr.repository.LoanRepository;
import com.libra.smr.repository.MemberRepository;
import org.springframework.stereotype.Service;
import com.libra.smr.entity.Book;
import com.libra.smr.entity.Member;
import com.libra.smr.exception.BookNotFoundException;
import com.libra.smr.exception.MemberNotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public LoanService(
            LoanRepository loanRepository,
            BookRepository bookRepository,
            MemberRepository memberRepository) {

        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public List<LoanResponseDto> getAllLoan(){
        return loanRepository.findAll()
                .stream()
                .map(LoanResponseDto::new)
                .toList();
    }

    public LoanResponseDto getLoanById(Long id){
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));

        return new LoanResponseDto(loan);
    }

    public LoanResponseDto createLoan(LoanRequestDto loanRequestDto) {

        Book book = bookRepository.findById(loanRequestDto.bookId())
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        Member member = memberRepository.findById(loanRequestDto.memberId())
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));

        if (book.getStatus() == BookStatus.BORROWED) {
            throw new IllegalStateException("Book has been borrowed");
        }

        LocalDate loanDate = LocalDate.now();

        Loan loan = new Loan();

        loan.setBook(book);
        loan.setMember(member);
        loan.setLoanDate(loanDate);
        loan.setReturnDate(loanDate.plusDays(7));
        book.setStatus(BookStatus.BORROWED);
        bookRepository.save(book);

        Loan savedLoan = loanRepository.save(loan);

        return new LoanResponseDto(savedLoan);
    }

    public LoanResponseDto renewLoan(Long id) {

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));

        if (loan.getRenewalCount() >= 3) {
            throw new IllegalStateException("Loan has reached the renewal limit");
        }

        loan.setReturnDate(loan.getReturnDate().plusDays(7));
        loan.setRenewalCount(loan.getRenewalCount() + 1);

        Loan updatedLoan = loanRepository.save(loan);

        return new LoanResponseDto(updatedLoan);
    }

    public LoanResponseDto returnLoan(Long id) {

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));

        Book book = loan.getBook();

        book.setStatus(BookStatus.AVAILABLE);
        bookRepository.save(book);

        return new LoanResponseDto(loan);
    }
}
