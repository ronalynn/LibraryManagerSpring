package com.librarymanagerspring.librarymanagerspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarymanagerspring.librarymanagerspring.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest

public class BookControllerTest {


//
//    private MockMvc mockMvc;
//
//    @Autowired
//    private WebApplicationContext wc;
//
//    public static final Book book = Book.builder()
//            .id(20L)
//            .title("testing book")
//            .author("testing author")
//            .year(2021L)
//            .pages(123L)
//            .build();
//
//    List<Book> books = new ArrayList<Book>();
//
//    private static final ObjectMapper MAPPER = new ObjectMapper() ;
//
//
//    @Before
//    public void setUp(){
//        mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
//    }
//
//    @Test
//    public void saveBook() throws JsonProcessingException, Exception {
//        MvcResult result = mockMvc.perform(put("/api/books")
//                .contentType(APPLICATION_JSON_VALUE)
//                .content(MAPPER.writeValueAsString(book)))
//                .andExpect(status().isOk())
//                .andReturn();
//        Book response = MAPPER.readValue(result.getResponse().getContentAsString(), Book.class);
//        assertEquals(book, response);
//    }
//
//    @Test
//    public void getBooks() throws JsonProcessingException, Exception {
//        MvcResult result = mockMvc.perform(get("/api/books")
//                .contentType(APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        List<Book> response = MAPPER.readValue(result.getResponse()
//                .getContentAsString(), new TypeReference<List<Book>>() {
//        });
//        assertEquals(books, response);
//    }
//
//    @Test
//    public void updateBook() throws Exception {
//        MvcResult result = mockMvc.perform(put("/api/books")
//                .contentType(APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        Book response = MAPPER.readValue(result.getResponse().getContentAsString(), Book.class);
//        assertEquals(book, response);
//    }
//
//    @Test
//    public void deleteBook() throws Exception {
//        mockMvc.perform(delete("/api/books/{id}", 14L)
//                .contentType(APPLICATION_JSON_VALUE))
//                .andExpect(status().isNoContent())
//                .andReturn();
//    }

}