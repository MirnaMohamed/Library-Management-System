package com.example.librarymanagementsystem.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BorrowResponseDto (
        Long borrowingRecordId,
        LocalDateTime borrowingDate,
        LocalDateTime returnDate,
        Long patron_id,
        Long book_id
){
}
