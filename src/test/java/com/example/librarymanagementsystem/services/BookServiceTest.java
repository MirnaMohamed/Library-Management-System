package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.dto.request.BookRequestDto;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.mapper.BookMapper;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.service.BookService;
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
public class BookServiceTest {
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private BookMapper bookMapper;
    private final BookService bookService;

    @Autowired
    public BookServiceTest(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @Test
    public void whenFindAll_ReturnBookList(){
        // Repository Mockup
        Book book= Book.builder()
                .bookId(1L).title("First Book Title!")
                .author("First Author").isbn("ht544b5h4vfg")
                .publicationYear(2000)
                .build();
        Book book2=Book.builder()
                .bookId(2L).title("Second Banner Title!").author("Second Author Description")
                .isbn("www.second-banner-image.com")
                .publicationYear(2020)
                .build();
        List<Book> bookList= Arrays.asList(book,book2);
        given(bookRepository.findAll()).willReturn(bookList);

        // Assertion Test
        assertThat(bookService.getAllBooks())
                .contains(bookMapper.bookToBookResponseDto(book),bookMapper.bookToBookResponseDto(book2))
                .hasSize(2);
    }

    @Test
    public void whenFindById_ReturnBook(){
        // Repository Mockup
        Book book= Book.builder()
                .bookId(1L).title("First Book Title!")
                .author("First Author").isbn("ht544b5h4vfg")
                .publicationYear(2000)
                .build();
        given(bookRepository.findById(Mockito.anyLong())).willReturn(Optional.ofNullable(book));

        // Assertion Test
        assertThat(bookService.getBookById(1L))
                .isEqualTo(bookMapper.bookToBookResponseDto(book));
    }

    @Test
    public void whenAdd_ReturnTrue(){
        // Repository Mockup
        Book book= Book.builder()
                .bookId(1L).title("First Book Title!")
                .author("First Author").isbn("ht544b5h4vfg")
                .publicationYear(2000)
                .build();
        given(bookRepository.save(Mockito.any(Book.class))).willReturn(book);

        // Assertion Test
        assertThat(bookService.addBook(Mockito.mock(BookRequestDto.class)))
                .isEqualTo(bookMapper.bookToBookResponseDto(book));
    }

    @Test
    void whenDeleteBook_thenReturnTrue(){
        Book existingBook = Book.builder()
                .bookId(1L).author("author 1")
                .publicationYear(2020)
                .isbn("fkrinri")
                .build();
        when(bookRepository.findById( 1L))
                .thenReturn(Optional.ofNullable(existingBook));
        bookService.deleteBook(1L);
        Mockito.verify(bookRepository).deleteById(1L); //Test delete method was fired
    }
}
