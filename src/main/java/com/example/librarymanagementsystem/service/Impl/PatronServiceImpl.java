package com.example.librarymanagementsystem.service.Impl;

import com.example.librarymanagementsystem.dto.request.PatronRequestDto;
import com.example.librarymanagementsystem.dto.response.PatronResponseDto;
import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.mapper.PatronMapper;
import com.example.librarymanagementsystem.repository.PatronRepository;
import com.example.librarymanagementsystem.service.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatronServiceImpl implements PatronService {
    private final PatronRepository patronRepository;
    private final PatronMapper patronMapper;

    @Override
    public List<PatronResponseDto> getAllPatrons() {
        return patronRepository.findAll().stream().map(patronMapper::ToPatronResponseDto).toList();
    }

    @Override
    public PatronResponseDto getPatronById(Long id) {
        Patron patron = patronRepository.findById(id).orElseThrow( () ->
                new NotFoundException("There is no patron with this id"));
        return patronMapper.ToPatronResponseDto(patron);
    }

    @Override
    public PatronResponseDto addPatron(PatronRequestDto patronRequestDto) {
        Patron patron = patronMapper.patronRequestDtoToPatron(patronRequestDto);
        return patronMapper.ToPatronResponseDto(patronRepository.save(patron));
    }

    @Override
    public PatronResponseDto updatePatron(Long id, PatronRequestDto patronRequestDto) {
        if(!patronRepository.existsById(id)) {
            throw new NotFoundException("There is no patron with this id! Try to add a new patron");
        }
        else {
            Patron patron = patronMapper.patronRequestDtoToPatron(patronRequestDto);
            patron.setPatronId(id);
            return patronMapper.ToPatronResponseDto(patronRepository.save(patron));
        }
    }

    @Override
    public void deletePatron(Long id) {
        patronRepository.findById(id).orElseThrow(()->
                new NotFoundException("There is no patron with this id! Couldn't delete the patron"));
        patronRepository.deleteById(id);
    }
}
