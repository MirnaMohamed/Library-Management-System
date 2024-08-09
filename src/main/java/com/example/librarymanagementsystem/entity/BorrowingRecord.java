package com.example.librarymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "borrow_records")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrowingRecordId;
    private LocalDateTime borrowingDate;
    private LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book borrowedBook;
}
