package com.example.librarymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "patron")
@RequiredArgsConstructor
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patronId;
    private String patronEmail;
    private String userName;
    private String patronPhone;

    @OneToMany(mappedBy = "patron")
    private List<BorrowingRecord> borrowingRecords;
}
