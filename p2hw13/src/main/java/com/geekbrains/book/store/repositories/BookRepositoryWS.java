package com.geekbrains.book.store.repositories;

import com.geekbrains.book.store.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryWS extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
}
