package com.example.librarymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "patron")
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
