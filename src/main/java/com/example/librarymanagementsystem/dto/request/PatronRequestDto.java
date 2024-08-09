package com.example.librarymanagementsystem.dto.request;

public record PatronRequestDto(
        String patronEmail,
        String userName,
        String patronPhone
) {
}
