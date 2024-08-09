package com.example.librarymanagementsystem.mapper;

import com.example.librarymanagementsystem.dto.request.PatronRequestDto;
import com.example.librarymanagementsystem.dto.response.PatronResponseDto;
import com.example.librarymanagementsystem.entity.Patron;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatronMapper {
    PatronResponseDto ToPatronResponseDto(Patron patron);
    Patron patronRequestDtoToPatron(PatronRequestDto patronRequestDto);
}
