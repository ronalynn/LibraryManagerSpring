package com.librarymanagerspring.LibraryManagerSpring.service;

import com.librarymanagerspring.LibraryManagerSpring.model.Book;
import com.librarymanagerspring.LibraryManagerSpring.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @Autowired
    @InjectMocks
    private BookService bookService;
    private Book book1;
    private Book book2;
    List<Book> bookList;

    @BeforeEach
    public void setUp(){
        bookList = new ArrayList<>();
        book1 = new Book(1L, "Book One", "Author One", 1111L, 11L);
        book2 = new Book(2L, "Book Two", "Author Two", 2222L, 22L);
        bookList.add(book1);
        bookList.add(book2);

    }

    @AfterEach
    public void tearDown() {
        book1 = book2 = null;
        bookList = null;
    }


    @Test
    void testGetBookById(){
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(book1));
        assertThat(bookService.getBookById(book1.getId())).isEqualTo(book1);
    }

    @Test
    void testGetBooks(){
        bookRepository.save(book1);

        when(bookRepository.findAll()).thenReturn(bookList);

        List<Book> bookList1 = bookService.getBooks();

        assertEquals(bookList1, bookList);

        verify(bookRepository, times(1)).save(book1);
        verify(bookRepository,times(1)).findAll();
    }

    @Test
    void testAddBook(){
        when(bookRepository.save(any())).thenReturn(book1);
        bookService.addBook(book1);
        verify(bookRepository, times(1)).save(any());
    }

    @Test
    void testDelete(){
        Long book1Id = 1L;
        bookService.deleteBook(book1.getId());
        verify(bookRepository,times(1)).deleteById(book1Id);
    }
}