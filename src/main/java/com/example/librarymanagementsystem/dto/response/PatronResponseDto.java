package com.example.librarymanagementsystem.dto.response;

import lombok.Builder;

@Builder
public record PatronResponseDto (
        Long patronId,
        String patronEmail,
        String userName,
        String patronPhone
) {
}
