package com.example.librarymanagementsystem.mapper;

import com.example.librarymanagementsystem.dto.response.BorrowResponseDto;
import com.example.librarymanagementsystem.entity.BorrowingRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class BorrowRecordToBorrowResponseDtoMapper implements Function<BorrowingRecord, BorrowResponseDto> {

    @Override
    public BorrowResponseDto apply(BorrowingRecord borrowingRecord) {
        return BorrowResponseDto.builder()
                .borrowingRecordId(borrowingRecord.getBorrowingRecordId())
                .borrowingDate(borrowingRecord.getBorrowingDate())
                .book_id(borrowingRecord.getBorrowedBook().getBookId())
                .patron_id(borrowingRecord.getPatron().getPatronId())
                .returnDate(borrowingRecord.getReturnDate())
                .build();
    }
}
