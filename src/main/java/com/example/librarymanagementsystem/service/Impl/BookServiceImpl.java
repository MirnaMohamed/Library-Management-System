package com.example.librarymanagementsystem.service.Impl;

import com.example.librarymanagementsystem.dto.request.BookRequestDto;
import com.example.librarymanagementsystem.dto.response.BookResponseDto;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.mapper.BookMapper;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::bookToBookResponseDto).toList();
    }

    @Override
    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow( () ->
                new NotFoundException("There is no book with this id"));
        return bookMapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        Book book = bookMapper.bookRequestDtoToBook(bookRequestDto);
        return bookMapper.bookToBookResponseDto(bookRepository.save(book));
    }

    @Override
    public BookResponseDto updateBook(Long id, BookRequestDto bookRequestDto) {
        if(!bookRepository.existsById(id)) {
            throw new NotFoundException("There is no book with this id! Try to add a new book");
        }
        else {
            Book book = bookMapper.bookRequestDtoToBook(bookRequestDto);
            book.setBookId(id);
            return bookMapper.bookToBookResponseDto(bookRepository.save(book));
        }
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.findById(id).orElseThrow(()->
                new NotFoundException("There is no book with this id! Couldn't delete the book"));
        bookRepository.deleteById(id);
    }

}
