package com.example.librarymanagementsystem.dto.response;

import lombok.Builder;

@Builder
public record UserRegisterResponseDto (
        String firstName,
        String lastName,
        String email
){
}
