package com.example.librarymanagementsystem.dto.request;

import lombok.Builder;

@Builder
public record BookRequestDto (
    String title,
    String author,
    Integer publicationYear,
    String isbn
    ){
}
