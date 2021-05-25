package com.librarymanagerspring.LibraryManagerSpring.service;

import com.librarymanagerspring.LibraryManagerSpring.exception.BookNotFoundException;
import com.librarymanagerspring.LibraryManagerSpring.model.Book;
import com.librarymanagerspring.LibraryManagerSpring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("book with id: " + id + " cannot be found." ));
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}