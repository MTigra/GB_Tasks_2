package com.geekbrains.book.store.endpoints;

import com.geekbrains.book.store.GetAllBooksResponse;
import com.geekbrains.book.store.GetBookByIdRequest;
import com.geekbrains.book.store.GetBookByIdResponse;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.services.BookServiceWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BooksEndpoint {
    private static final String NAMESPACE_URI = "http://book.geekbrains.com/store";

    private BookServiceWS bookService;

    @Autowired
    public BooksEndpoint(BookServiceWS bookService) {
        this.bookService = bookService;
    }

    //<x:Envelope xmlns:x="http://schemas.xmlsoap.org/soap/envelope/" xmlns:bb="http://book.geekbrains.com/store">
    //   <x:Header/>
    //    <x:Body>
    //        <bb:getBookByIdRequest>
    //        <bb:id>10</bb:id>
    //        </bb:getBookByIdRequest>
    //    </x:Body>
    //</x:Envelope>
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookByIdRequest")
    @ResponsePayload
    public GetBookByIdResponse getBookById(@RequestPayload GetBookByIdRequest request) {
        GetBookByIdResponse response = new GetBookByIdResponse();
        response.setBook(bookService.findById(request.getId()));
        return response;
    }


    // <x:Envelope xmlns:x="http://schemas.xmlsoap.org/soap/envelope/" xmlns:bb="http://book.geekbrains.com/store">
    //   <x:Header/>
    //    <x:Body>
    //        <bb:getAllBooksRequest>
    //        </bb:getAllBooksRequest>
    //    </x:Body>
    //</x:Envelope>
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllBooksRequest")
    @ResponsePayload
    public GetAllBooksResponse getAllBooks() {
        GetAllBooksResponse response = new GetAllBooksResponse();
        response.getBook().addAll(bookService.findAll());
        return response;
    }
}