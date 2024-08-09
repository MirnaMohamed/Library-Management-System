package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.response.BorrowResponseDto;

public interface BorrowingRecordService {
    BorrowResponseDto borrowBook(Long bookId, Long patronId);
    BorrowResponseDto returnBook(Long bookId, Long patronId);
}
