package com.example.bookku_be.service;

import com.example.bookku_be.config.UserDetailsImpl;
import com.example.bookku_be.domain.Book;
import com.example.bookku_be.domain.Member;
import com.example.bookku_be.domain.Memo;
import com.example.bookku_be.dto.ReqDto.BookReqDto;
import com.example.bookku_be.dto.ResDto.BookResDto;
import com.example.bookku_be.dto.ResDto.GlobalResDto;
import com.example.bookku_be.exception.CustomException;
import com.example.bookku_be.exception.ErrorCode;
import com.example.bookku_be.repository.BookRepository;
import com.example.bookku_be.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final MemoRepository memoRepository;
//    private final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Transactional
    public GlobalResDto<?> addBook(BookReqDto bookReqDto, UserDetailsImpl userDetails) {

        Book book = new Book(bookReqDto, userDetails.getMember());
        bookRepository.save(book);

        return GlobalResDto.success(null, "success create book");
    }

    @Transactional
    public GlobalResDto<?> deleteBook(Long bookId, UserDetailsImpl userDetails) {

        Book book = bookRepository.findById(bookId).orElseThrow(
                ()-> new CustomException(ErrorCode.NOT_FOUND_BOOK)
        );

        // 책의 작성자와 현재 사용자가 일치하는지 확인
        if (!book.getMember().getMemberId().equals(userDetails.getMember().getMemberId())) {
            throw new CustomException(ErrorCode.NOT_MATCH_MEMBER);
        }

        bookRepository.deleteById(bookId);

        List<Memo> memoList = memoRepository.findMemoByBook(book);
        memoRepository.deleteAll(memoList);

        return GlobalResDto.success(null, "success delete book");
    }

    @Transactional
    public GlobalResDto<?> getAllBook(UserDetailsImpl userDetails) {

        Member member = userDetails.getMember();
        List<Book> bookList = bookRepository.findBookByMember(member);

        List<BookResDto> bookResDtoList = bookList.stream()
                .map(BookResDto::new).toList();

        return GlobalResDto.success(bookResDtoList, "success read books");
    }


}
