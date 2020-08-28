package com.geekbrains.book.store.services;

import com.geekbrains.book.store.Book;
import com.geekbrains.book.store.exceptions.ResourceNotFoundException;
import com.geekbrains.book.store.repositories.BookRepository;
import com.geekbrains.book.store.repositories.BookRepositoryWS;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceWS {

    private BookRepositoryWS bookRepository;

    @Transactional
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    public Page<Book> findAll(Specification<Book> spec, int page, int size) {
        return bookRepository.findAll(spec, PageRequest.of(page, size));
    }

    @Transactional
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book with id: " + id + " not found"));
    }

    @Transactional
    public Book saveOrUpdate(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
