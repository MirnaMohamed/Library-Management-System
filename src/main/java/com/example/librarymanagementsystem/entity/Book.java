package com.example.librarymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String title;
    private String author;
    private Integer publicationYear;
    private String isbn;

    @OneToMany(mappedBy = "borrowedBook")
    private List<BorrowingRecord> borrowingRecords;
}
