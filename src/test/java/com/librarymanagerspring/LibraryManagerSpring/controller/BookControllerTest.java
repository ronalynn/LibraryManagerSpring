package com.librarymanagerspring.LibraryManagerSpring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarymanagerspring.LibraryManagerSpring.model.Book;
import com.librarymanagerspring.LibraryManagerSpring.service.BookService;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest{

    @Mock
    private BookService bookService;
    private Book book1;
    private Book book2;
    private List<Book> bookList;

    @InjectMocks
    private BookController bookController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        book1 = new Book(1L, "Book One", "Author One", 1111L, 11L);
        book2 = new Book(2L, "Book Two", "Author Two", 2222L, 22L);

        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @AfterEach
    void tearDown() {
        book1 = null;
    }

    @Test
    void testGetBooks() throws Exception{
        when(bookService.getBooks()).thenReturn(bookList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(book1))).
                andDo(MockMvcResultHandlers.print());

        verify(bookService).getBooks();
        verify(bookService, times(1)).getBooks();

    }

    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void testAddBook() throws Exception{
        doReturn(book1).when(bookService).addBook(book1);

        mockMvc.perform(post("/api/books").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(book1))).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.title", is("Book One")));

        verify(bookService, times(1)).addBook(any());

    }

    @Test
    void testGetBookById() throws Exception{
        when(bookService.getBookById(book2.getId())).thenReturn(book2);

        mockMvc.perform(get("/api/books/2").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(book1))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(jsonPath("$.title", is("Book Two"))).
                andDo(MockMvcResultHandlers.print());
        assertEquals(2, bookService.getBookById(book2.getId()).getId());
    }

    @Test
    void testDeleteBook() throws Exception{
        bookService.deleteBook(book1.getId());

        mockMvc.perform(delete("/api/books/1").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(book1))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }


}