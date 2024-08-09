package com.example.librarymanagementsystem.dto.request;

public record UserLoginRequestDto (
        String email,
        String password
) {
}
