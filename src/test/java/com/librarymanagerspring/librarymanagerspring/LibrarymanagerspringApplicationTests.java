package com.librarymanagerspring.librarymanagerspring;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import com.librarymanagerspring.librarymanagerspring.model.Book;
import com.librarymanagerspring.librarymanagerspring.repository.BookRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import java.util.List;

@SpringBootTest
class LibrarymanagerspringApplicationTests {

	@Autowired
	BookRepository bookRepository;

	@Test
	public void testAddBook(){
		Book bookTestOne = new Book();
		bookTestOne.setId(100L);
		bookTestOne.setTitle("A Book About Unit Testing");
		bookTestOne.setAuthor("Ronalyn Nanong");
		bookTestOne.setYear(2021L);
		bookTestOne.setPages(100L);
		bookRepository.save(bookTestOne);
		assertNotNull(bookRepository.findById(100L).get());

		Book bookTestTwo = new Book();
		bookTestTwo.setId(101L);
		bookTestTwo.setTitle("Unit Testing Update Book");
		bookTestTwo.setAuthor("Ronalyn Nanong");
		bookTestTwo.setYear(2021L);
		bookTestTwo.setPages(100L);
		bookRepository.save(bookTestTwo);
		assertNotNull(bookRepository.findById(101L).get());

		Book bookTestThree = new Book();
		bookTestThree.setId(1022L);
		bookTestThree.setTitle("Unit Testing Delete Book");
		bookTestThree.setAuthor("Ronalyn Nanong");
		bookTestThree.setYear(2021L);
		bookTestThree.setPages(101L);
		bookRepository.save(bookTestThree);
		assertNotNull(bookRepository.findById(1022L).get());
		//assertEquals(3, bookRepository.findAll().size());
	}

	@Test
	public void getAllBooks(){
		List<Book> bookList = bookRepository.findAll();
		assertTrue(bookList.size() > (0));
	}

	@Test
	//@Description("hello")
	public void testGetBookById () {
		Book book = bookRepository.findById(100L).get();
		assertEquals("A Book About Unit Testing", bookRepository.findById(100L).get().getTitle());
		assertEquals("Ronalyn Nanong", bookRepository.findById(100L).get().getAuthor());
	}

	@Test
	public void testUpdateBook () {
		Book book = bookRepository.findById(101L).get();
		book.setTitle("Updated Unit Testing Book!!");
		bookRepository.save(book);
		assertNotEquals("Unit Testing Update Book", bookRepository.findById(100L).get().getTitle());
	}

	@Test
	public void testDelete(){
		bookRepository.deleteById(1022L);
		assertFalse(bookRepository.existsById(1022L));
	}

}
