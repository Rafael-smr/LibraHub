package com.libra.smr.service;

import com.libra.smr.dto.BookRequestDto;
import com.libra.smr.dto.BookResponseDto;
import com.libra.smr.entity.Book;
import com.libra.smr.enums.BookStatus;
import com.libra.smr.exception.BookNotFoundException;
import com.libra.smr.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookResponseDto::new)
                .toList();
    }

    public BookResponseDto getBookById(long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        return new BookResponseDto(book);
    }

    public BookResponseDto createBook(BookRequestDto bookRequestDto) {

        Book book = new Book();

        book.setTitle(bookRequestDto.title());
        book.setAuthor(bookRequestDto.author());
        book.setStatus(BookStatus.AVAILABLE);

        Book savedBook = bookRepository.save(book);

        return new BookResponseDto(savedBook);
    }

    public void deleteBook(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));

        bookRepository.delete(book);
    }

    public BookResponseDto updateBook(Long id, BookRequestDto bookRequestDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        book.setTitle(bookRequestDto.title());
        book.setAuthor(bookRequestDto.author());

        Book updatedBook = bookRepository.save(book);

        return new BookResponseDto(updatedBook);
    }
}
