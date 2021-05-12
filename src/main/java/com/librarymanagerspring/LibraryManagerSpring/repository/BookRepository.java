package com.librarymanagerspring.LibraryManagerSpring.repository;

import com.librarymanagerspring.LibraryManagerSpring.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
