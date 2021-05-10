package com.librarymanagerspring.librarymanagerspring.service;

import com.librarymanagerspring.librarymanagerspring.exception.BookNotFoundException;
import com.librarymanagerspring.librarymanagerspring.model.Book;
import com.librarymanagerspring.librarymanagerspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("book cannot be found"));
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
