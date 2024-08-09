package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.request.BookRequestDto;
import com.example.librarymanagementsystem.dto.response.BookResponseDto;
import com.example.librarymanagementsystem.factory.impl.SuccessResponseFactory200;
import com.example.librarymanagementsystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final SuccessResponseFactory200 responseFactory;

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        List<BookResponseDto> books = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(books,"Books returned successfully "));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findBookById(@PathVariable Long id) {
        BookResponseDto book = bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(book,"Book returned successfully "));
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto book = bookService.addBook(bookRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(book,"Book added successfully "));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto book = bookService.updateBook(id, bookRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(book,"Book updated successfully "));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> addBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(null,"Book deleted successfully "));
    }
}
