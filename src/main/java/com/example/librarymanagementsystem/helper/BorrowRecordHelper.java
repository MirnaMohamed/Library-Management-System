package com.example.librarymanagementsystem.helper;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.BorrowingRecord;
import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.BorrowRecordRepository;
import com.example.librarymanagementsystem.repository.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

@Component
@RequiredArgsConstructor
public class BorrowRecordHelper {
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public BorrowingRecord findBorrowRecordByPatronIdAndBookId(Long bookId, Long patronId ){
        Book book = findBookByIdOrElseThrowNotFound(bookId);
        Patron patron = patronRepository.findById(patronId).orElse(null);
        return borrowRecordRepository.findByBorrowedBookAndPatron(book, patron).orElseThrow(
                ()-> new NotFoundException("There is no borrowing record with patron id : "+ patronId +" and bookId : "+ bookId + "!")
        );
    }
    public Book findBookByIdOrElseThrowNotFound(Long bookId){
        return bookRepository.findById(bookId).orElseThrow(
                ()-> new NotFoundException("There is no book with bookId : "+ bookId + "!"));
    }

    public Patron findPatronByIdOrElseThrowNotFound(Long patronId){
        return patronRepository.findById(patronId).orElseThrow(
                ()-> new NotFoundException("There is no patron with Id : "+ patronId + "!"));
    }
}
