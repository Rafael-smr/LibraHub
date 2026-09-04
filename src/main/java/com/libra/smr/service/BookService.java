package com.libra.smr.service;

import com.libra.smr.dto.BookRequestDto;
import com.libra.smr.dto.BookResponseDto;
import com.libra.smr.entity.Book;
import com.libra.smr.enums.BookStatus;
import com.libra.smr.exception.BookNotFoundException;
import com.libra.smr.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookResponseDto getBookById(long id) throws BookNotFoundException {

        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()) {
            throw new BookNotFoundException("Book not found");
        }

        return new BookResponseDto(book.get());
    }

    public Book createBook(BookRequestDto bookRequestDto) {

        Book book = new Book();

        book.setTitle(bookRequestDto.title());
        book.setAuthor(bookRequestDto.author());
        book.setStatus(BookStatus.AVAILABLE);

        return bookRepository.save(book);
    }

    public void deleteBook(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));

        bookRepository.delete(book);
    }

    public void updateBook(Book book) {
        //percebi que esse seria mais dificil do que eu pensei
    }
}
