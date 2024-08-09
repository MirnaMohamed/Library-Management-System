package com.example.librarymanagementsystem.service.Impl;

import com.example.librarymanagementsystem.dto.response.BorrowResponseDto;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.BorrowingRecord;
import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.helper.BorrowRecordHelper;
import com.example.librarymanagementsystem.mapper.BorrowRecordToBorrowResponseDtoMapper;
import com.example.librarymanagementsystem.repository.BorrowRecordRepository;
import com.example.librarymanagementsystem.service.BorrowingRecordService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BorrowingRecordServiceImpl implements BorrowingRecordService {
    private final BorrowRecordRepository borrowRepository;
    private final BorrowRecordHelper borrowRecordHelper;
    private final BorrowRecordToBorrowResponseDtoMapper toBorrowRecordResponseDtoMapper;

    @Override
    @Transactional
    public BorrowResponseDto borrowBook(Long bookId, Long patronId) {
        Book borrowedBook = borrowRecordHelper.findBookByIdOrElseThrowNotFound(bookId);
        Patron patron = borrowRecordHelper.findPatronByIdOrElseThrowNotFound(patronId);

        BorrowingRecord borrowingRecord = BorrowingRecord.builder()
                .borrowedBook(borrowedBook)
                .patron(patron)
                .borrowingDate(LocalDateTime.now()).build();
        return toBorrowRecordResponseDtoMapper.apply(borrowRepository.save(borrowingRecord));
    }

    @Override
    public BorrowResponseDto returnBook(Long bookId, Long patronId) {
        BorrowingRecord borrowingRecord = borrowRecordHelper
                .findBorrowRecordByPatronIdAndBookId(bookId, patronId);
        borrowingRecord.setReturnDate(LocalDateTime.now());
        return toBorrowRecordResponseDtoMapper.apply(borrowRepository.save(borrowingRecord));
    }
}
