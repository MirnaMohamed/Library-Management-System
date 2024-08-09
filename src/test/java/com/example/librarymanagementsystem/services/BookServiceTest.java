package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.User;
import com.example.librarymanagementsystem.helper.UserHelper;
import com.example.librarymanagementsystem.mapper.BookMapper;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {
    @MockBean
    private BookRepository bookRepository;
//    @MockBean
//    private BannerRequestDtoToBanner mockBannerRequestMapper;
    @MockBean
    private BookMapper bookMapper;
    @MockBean
    private User userMock;
    @MockBean
    private UserHelper userHelper;
    private final BookService bookService;
//    private final BannerToBannerResponseDto toBannerResponseDto;

    @Autowired
    public BookServiceTest(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @BeforeEach
    void init(){
        when(userMock.getUserId()).thenReturn(1L);
        Mockito.when(userHelper.findUserByIdOrThrowNotFoundException(1L)).thenReturn(userMock);
    }

    @Test
    public void whenFindAll_ReturnBannerList(){
        // Repository Mockup
        Book book= Book.builder()
                .bookId(1L).title("First Book Title!")
                .author("First Author Description").isbn("ht544b5h4vfg")
                .publicationYear(2000)
                .build();
        Book book2=Book.builder()
                .bookId(2L).title("Second Banner Title!").author("Second Author Description").isbn("www.second-banner-image.com")
                .publicationYear(2020)
                .build();
        List<Book> bannerList= Arrays.asList(book,book2);
        given(bookRepository.findAll()).willReturn(bannerList);

        // Assertion Test
        assertThat(bookService.getAllBooks())
                .contains(bookMapper.bookToBookResponseDto(book),bookMapper.bookToBookResponseDto(book2))
                .hasSize(2);
    }
}
