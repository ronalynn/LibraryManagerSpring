package com.librarymanagerspring.LibraryManagerSpring.repository;

import com.librarymanagerspring.LibraryManagerSpring.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    private Book book;

    @BeforeEach
    public void setUp(){
        book = new Book(1L, "Book One", "Author One", 1111L, 11L);
    }

    @AfterEach
    public void tearDown() {
        bookRepository.deleteAll();
        book = null;
    }

    @Test
    public void testAdd(){
        bookRepository.save(book);
        Book returnBook = bookRepository.findById(book.getId()).get();

        assertEquals(1, returnBook.getId());
    }

    //testFindAll
    @Test
    public void testFindAll(){
        Book book2 = new Book(2L, "Book Two", "Author Two", 2222L, 22L);
        Book book3 = new Book(3L, "Book Three", "Author Three", 3333L, 33L);
        bookRepository.save(book2);
        bookRepository.save(book3);

        List<Book> bookList = (List<Book>) bookRepository.findAll();
        assertNotNull(bookList);
        assertEquals(2, bookList.size());
    }


    //testFindById
    @Test
    public void testFindById() {
        Book book2 = new Book(2L, "Book Two", "Author Two", 2222L, 22L);
        Book book3 = bookRepository.save(book2);

        Optional<Book> optional = bookRepository.findById(book3.getId());
//        assertEquals(book3.getId(), optional.get().getId());
//        assertEquals(book3.getTitle(), optional.get().getTitle());
        assertEquals(book3, optional.get());
    }


//    //Does not work yet
//    @Test
//    public void testDeleteById(){
//        Book book = new Book(4L, "Book Four", "Author Four", 4444L, 44L);
//        bookRepository.save(book);
//        bookRepository.deleteById(book.getId());
//
//        Optional optional = bookRepository.findById(book.getId());
//        assertEquals(Optional.empty(), optional);
//    }

}