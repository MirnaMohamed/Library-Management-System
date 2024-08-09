package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.response.BorrowResponseDto;
import com.example.librarymanagementsystem.factory.impl.SuccessResponseFactory200;
import com.example.librarymanagementsystem.service.BorrowingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BorrowRecordController {

    private final BorrowingRecordService borrowingRecordService;
    private final SuccessResponseFactory200 responseFactory;

    @PostMapping("/borrow/{book_id}/patron/{patron_id}")
    public ResponseEntity<?> borrowBook(@PathVariable Long book_id,
                                     @PathVariable Long patron_id) {
        BorrowResponseDto borrowResponseDto = borrowingRecordService.borrowBook(book_id, patron_id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(borrowResponseDto,"Borrow record added successfully "));
    }

    @PutMapping("/return/{book_id}/patron/{patron_id}")
    public ResponseEntity<?> returnBook(@PathVariable Long book_id,
                                        @PathVariable Long patron_id) {
        BorrowResponseDto borrowResponseDto = borrowingRecordService.returnBook(book_id, patron_id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(borrowResponseDto,"Book returned successfully "));
    }
}
