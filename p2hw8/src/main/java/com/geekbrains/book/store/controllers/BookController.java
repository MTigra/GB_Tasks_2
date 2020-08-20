package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.utils.BookFilter;
import com.geekbrains.book.store.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String showAllBooks(Model model,
                               @RequestParam(name = "p", defaultValue = "1") Integer pageIndex,
                               @RequestParam MultiValueMap<String, String> params
    ) {
        BookFilter bookFilter = new BookFilter(params);
        Page<Book> page = bookService.findAll(bookFilter.getSpec(), pageIndex - 1, 5);
        model.addAttribute("books", page.getContent());
        model.addAttribute("page", page);
        MultiValueMap<String, String> paramsWithoutPage = new LinkedMultiValueMap<>(params);
        paramsWithoutPage.remove("p");
        model.addAttribute("filteringQuery", Utils.paramsMapToFilteringString(paramsWithoutPage));
        model.addAttribute("pageCount", page.getTotalPages());
        return "store-page";
    }

    // Эта часть кода будет сильно скорректирована после темы Spring REST
    @GetMapping("/api/books")
    @ResponseBody
    @CrossOrigin("*")
    public GetBooksResponse getBooks(@RequestParam(name = "p", defaultValue = "1") Integer pageIndex,
                                  @RequestParam MultiValueMap<String, String> params) {

        BookFilter bookFilter = new BookFilter(params);
        Page<Book> page = bookService.findAll(bookFilter.getSpec(), pageIndex - 1, 5);
        MultiValueMap<String, String> paramsWithoutPage = new LinkedMultiValueMap<>(params);
        paramsWithoutPage.remove("p");
        return new GetBooksResponse(page.getTotalPages(), page.getContent(), page.getNumber());
    }
}

@Data
@AllArgsConstructor
class GetBooksResponse{
    private int pageCount;
    private List<Book> books;
    private int currentPage;
}