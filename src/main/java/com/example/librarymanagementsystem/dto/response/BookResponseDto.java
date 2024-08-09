package com.example.librarymanagementsystem.dto.response;

public record BookResponseDto (
        String title,
        String author,
        Integer publicationYear,
        String isbn
    ){
}
