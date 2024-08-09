package com.example.librarymanagementsystem.services;


import com.example.librarymanagementsystem.dto.request.PatronRequestDto;
import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.mapper.PatronMapper;
import com.example.librarymanagementsystem.repository.PatronRepository;
import com.example.librarymanagementsystem.service.PatronService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PatronServiceTest {
    @MockBean
    private PatronRepository patronRepository;
    @MockBean
    private PatronMapper patronMapper;
    private final PatronService patronService;

    @Autowired
    public PatronServiceTest(PatronService patronService, PatronMapper patronMapper) {
        this.patronService = patronService;
        this.patronMapper = patronMapper;
    }

    @Test
    public void whenFindAll_ReturnPatronList(){
        // Repository Mockup
        Patron patron= Patron.builder()
                .patronId(1L).userName("First Patron")
                .patronEmail("patron@gmailcom").patronPhone("01023456789").build();
        Patron patron2= Patron.builder()
                .patronId(2L).userName("First Patron")
                .patronEmail("patron@gmailcom").patronPhone("01023456789").build();
        List<Patron> patronList= Arrays.asList(patron,patron2);
        given(patronRepository.findAll()).willReturn(patronList);

        // Assertion Test
        assertThat(patronService.getAllPatrons())
                .contains(patronMapper.ToPatronResponseDto(patron),patronMapper.ToPatronResponseDto(patron2))
                .hasSize(2);
    }

    @Test
    public void whenFindById_ReturnPatron(){
        // Repository Mockup
        Patron patron= Patron.builder()
                .patronId(1L).userName("First Patron")
                .patronEmail("patron@gmailcom").patronPhone("01023456789").build();
        given(patronRepository.findById(Mockito.anyLong())).willReturn(Optional.ofNullable(patron));

        // Assertion Test
        assertThat(patronService.getPatronById(1L))
                .isEqualTo(patronMapper.ToPatronResponseDto(patron));
    }

    @Test
    public void whenAdd_ReturnTrue(){
        // Repository Mockup
        Patron patron= Patron.builder()
                .patronId(1L).userName("First Patron")
                .patronEmail("patron@gmailcom").patronPhone("01023456789").build();
        given(patronRepository.save(Mockito.any(Patron.class))).willReturn(patron);

        // Assertion Test
        assertThat(patronService.addPatron(Mockito.mock(PatronRequestDto.class)))
                .isEqualTo(patronMapper.ToPatronResponseDto(patron));
    }


    @Test
    void whenDeletePatron_thenReturnTrue(){
        Patron existingPatron = Patron.builder()
                .patronId(1L).userName("First Patron")
                .patronEmail("patron@gmailcom").patronPhone("01023456789").build();
        when(patronRepository.findById( 1L))
                .thenReturn(Optional.ofNullable(existingPatron));
        patronService.deletePatron(1L);
        Mockito.verify(patronRepository).deleteById(1L); //Test delete method was fired
    }
}
