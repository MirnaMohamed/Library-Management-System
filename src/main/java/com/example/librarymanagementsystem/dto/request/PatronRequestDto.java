package com.example.librarymanagementsystem.dto.request;

import lombok.Builder;

@Builder
public record PatronRequestDto(
        String patronEmail,
        String userName,
        String patronPhone
) {
}
