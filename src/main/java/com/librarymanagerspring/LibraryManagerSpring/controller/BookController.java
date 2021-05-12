package com.librarymanagerspring.LibraryManagerSpring.controller;

import com.librarymanagerspring.LibraryManagerSpring.model.Book;
import com.librarymanagerspring.LibraryManagerSpring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //Get all books
    @GetMapping
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> allBooks = bookService.getBooks();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    //Get single book
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id){
        Book theBook = bookService.getBookById(id);
        return new ResponseEntity<>(theBook, HttpStatus.OK);
    }

    //Add new book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book theBook = bookService.addBook(book);
        return new ResponseEntity<>(theBook, HttpStatus.OK);
    }

    //Update existing book
    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        Book theBook = bookService.addBook(book); //if book doesnt exist, create a new book
        return new ResponseEntity<>(theBook, HttpStatus.OK);
    }

    //Delete a book
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
    }

}
