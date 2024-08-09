package com.example.librarymanagementsystem.mapper;

import com.example.librarymanagementsystem.dto.request.BookRequestDto;
import com.example.librarymanagementsystem.dto.response.BookResponseDto;
import com.example.librarymanagementsystem.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponseDto bookToBookResponseDto(Book book);
    Book bookRequestDtoToBook(BookRequestDto bookRequestDto);
}
