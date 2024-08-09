package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.request.BookRequestDto;
import com.example.librarymanagementsystem.dto.response.BookResponseDto;

import java.util.List;

public interface BookService {
    List<BookResponseDto> getAllBooks();
    BookResponseDto getBookById(Long id);
    BookResponseDto addBook(BookRequestDto bookRequestDto);
    BookResponseDto updateBook(Long id, BookRequestDto bookRequestDto);
    void deleteBook(Long id);
}
