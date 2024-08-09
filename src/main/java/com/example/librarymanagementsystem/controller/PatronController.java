package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.request.PatronRequestDto;
import com.example.librarymanagementsystem.dto.response.PatronResponseDto;
import com.example.librarymanagementsystem.factory.impl.SuccessResponseFactory200;
import com.example.librarymanagementsystem.service.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
@RequiredArgsConstructor
public class PatronController {
    private final PatronService patronService;
    private final SuccessResponseFactory200 responseFactory;

    @GetMapping
    public ResponseEntity<?> getAllPatrons() {
        List<PatronResponseDto> books = patronService.getAllPatrons();
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(books,"Patrons returned successfully "));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPatronById(@PathVariable Long id) {
        PatronResponseDto patron = patronService.getPatronById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(patron,"Patron returned successfully "));
    }

    @PostMapping
    public ResponseEntity<?> addPatron(@RequestBody PatronRequestDto patronRequestDto) {
        PatronResponseDto patron = patronService.addPatron(patronRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(patron,"Patron added successfully "));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatron(@PathVariable Long id, @RequestBody PatronRequestDto patronRequestDto) {
        PatronResponseDto patron = patronService.updatePatron(id, patronRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(patron,"Patron updated successfully "));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(null,"Patron deleted successfully "));
    }
}
