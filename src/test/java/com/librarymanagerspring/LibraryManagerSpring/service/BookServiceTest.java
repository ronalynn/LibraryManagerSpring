package com.librarymanagerspring.LibraryManagerSpring.service;

import com.librarymanagerspring.LibraryManagerSpring.model.Book;
import com.librarymanagerspring.LibraryManagerSpring.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService service;

    //Mock implementation of BookRepository
    @MockBean
    private BookRepository repository;

    @Test
    @DisplayName("Test getBookById()")
    void testGetBookById(){
        Book book = new Book(1L, "getBookById() test", "Ronalyn", 2021L, 123L);
        doReturn(Optional.of(book)).when(repository).findById(1L);

         Book returnedBook = service.getBookById(1L);

         assertNotNull(returnedBook);
         assertEquals(book, returnedBook);
         assertNotEquals(2, returnedBook.getId());
    }

    @Test
    @DisplayName("Test getBooks()")
    void testGetBooks(){
        Book bookOne = new Book(1L, "getBooks() test", "Ronalyn", 2021L, 123L);
        Book bookTwo = new Book(2L, "should error", "Ronalyn", 1234L, 123L);
        doReturn(Arrays.asList(bookOne, bookTwo)).when(repository).findAll();

        List<Book> books = service.getBooks();

        assertEquals(2, books.size(), "should return 2 books");
        assertNotEquals(3, books.size(), "should return 2 books");
    }

    @Test
    @DisplayName("Test addBook()")
    void testAddBook(){
        Book book = new Book(1L, "addBook() test", "Ronalyn", 2021L, 123L);
        doReturn(book).when(repository).save(any());

        Book returnedBook = service.addBook(book);

        assertNotNull(returnedBook, "Book should not be null");
        assertEquals(1, returnedBook.getId());
    }

}