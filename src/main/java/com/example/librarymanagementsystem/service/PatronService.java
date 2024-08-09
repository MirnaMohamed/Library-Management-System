package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.request.PatronRequestDto;
import com.example.librarymanagementsystem.dto.response.PatronResponseDto;

import java.util.List;

public interface PatronService {
    List<PatronResponseDto> getAllPatrons();
    PatronResponseDto getPatronById(Long id);
    PatronResponseDto addPatron(PatronRequestDto patronRequestDto);
    PatronResponseDto updatePatron(Long id, PatronRequestDto patronRequestDto);
    void deletePatron(Long id);
}
