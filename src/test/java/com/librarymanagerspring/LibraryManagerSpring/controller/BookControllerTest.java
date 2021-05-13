package com.librarymanagerspring.LibraryManagerSpring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarymanagerspring.LibraryManagerSpring.model.Book;
import com.librarymanagerspring.LibraryManagerSpring.service.BookService;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest{

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService service;

    @Autowired
    private MockMvc mockMvc;

    private List<Book> bookList;


    @Test
    void testGetBooks() throws Exception{
        bookList = new ArrayList<>();
        bookList.add(new Book(1L, "Book 1", "Author 1", 111L, 11L));
        bookList.add(new Book(2L, "Book 2", "Author 2", 222L, 22L));
        bookList.add(new Book(3L, "Book 3", "Author 3", 333L, 33L));
        given(service.getBooks()).willReturn(bookList);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(bookList.size())));

        assertEquals(3, bookList.size());
    }

    @Test
    void testGetBookById() throws Exception{
        Long bookId = 1L;
        Book book = new Book(bookId, "getBooksById() test", "Ronalyn", 2021L, 123L);
        given(service.getBookById(bookId)).willReturn(book);

        mockMvc.perform(get("/api/books/{id}", bookId))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.title", is(book.getTitle())))
                .andExpect(jsonPath("$.author", is(book.getAuthor())));

        //assertEquals(1, newBook.getId());
    }

    @Test
    void testAddBook() throws Exception{
        Book book = new Book(1L, "a book", "an author", 1234L, 12L);
        doReturn(book).when(service).addBook(book);

        //given(service.addBook(any())).willAnswer((invocation)-> invocation.getArgument(0));

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(book)))
                .andExpect(jsonPath("$.title", is("a book")));
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
//    void testUpdateBook() throws Exception{
//
//    }


    @Test
    void testDeleteBook() throws Exception{
        Long bookId = 1L;
        Book book = new Book(bookId, "test", "test", 123L, 33L);
        given(service.getBookById(bookId)).willReturn(book);
        doNothing().when(service).deleteBook(book.getId());

        mockMvc.perform(delete("/api/books/{id}", book.getId()))
                .andExpect(status().isOk());

        verify(service).deleteBook(any());
    }


}