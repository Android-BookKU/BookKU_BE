package com.example.bookku_be.service;

import com.example.bookku_be.config.UserDetailsImpl;
import com.example.bookku_be.domain.Book;
import com.example.bookku_be.domain.Member;
import com.example.bookku_be.dto.ReqDto.BookReqDto;
import com.example.bookku_be.dto.ResDto.BookResDto;
import com.example.bookku_be.dto.ResDto.GlobalResDto;
import com.example.bookku_be.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Transactional
    public GlobalResDto<?> addBook(BookReqDto bookReqDto, UserDetailsImpl userDetails) {

        Book book = new Book(bookReqDto, userDetails.getMember());
        bookRepository.save(book);

        return GlobalResDto.success(null, "success create book");
    }

    @Transactional
    public GlobalResDto<?> getAllBook(UserDetailsImpl userDetails) {

        try {
            Member member = userDetails.getMember();
            List<Book> bookList = bookRepository.findBookByMember(member);

            List<BookResDto> bookResDtoList = bookList.stream()
                    .map(BookResDto::new).toList();

            return GlobalResDto.success(bookResDtoList, "success read books");
        } catch (Exception e) {
            logger.error("Failed to read books", e);
            return GlobalResDto.fail("Failed to read books");
        }
    }


}
