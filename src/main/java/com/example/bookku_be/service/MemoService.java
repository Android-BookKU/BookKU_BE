package com.example.bookku_be.service;

import com.example.bookku_be.config.UserDetailsImpl;
import com.example.bookku_be.domain.Book;
import com.example.bookku_be.domain.Memo;
import com.example.bookku_be.dto.ReqDto.MemoReqDto;
import com.example.bookku_be.dto.ResDto.GlobalResDto;
import com.example.bookku_be.dto.ResDto.MemoResDto;
import com.example.bookku_be.exception.CustomException;
import com.example.bookku_be.exception.ErrorCode;
import com.example.bookku_be.repository.BookRepository;
import com.example.bookku_be.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final BookRepository bookRepository;
    private final MemoRepository memoRepository;

    @Transactional
    public GlobalResDto<?> addMemo(Long bookId, MemoReqDto memoReqDto, UserDetailsImpl userDetails) {

        Book book = bookRepository.findById(bookId).orElseThrow(
                ()-> new CustomException(ErrorCode.NOT_FOUND_BOOK)
        );

        Memo memo = new Memo(memoReqDto, book, userDetails.getMember());
        memoRepository.save(memo);

        return GlobalResDto.success(null, "success create memo");
    }

    @Transactional
    public GlobalResDto<?> updateMemo(Long bookId, Long memoId, UserDetailsImpl userDetails) {

    }

    @Transactional
    public GlobalResDto<?> deleteMemo(Long bookId, Long memoId, UserDetailsImpl userDetails) {

    }

    @Transactional
    public GlobalResDto<?> getAllMemo(Long bookId) {

        Book book = bookRepository.findById(bookId).orElseThrow(
                ()-> new CustomException(ErrorCode.NOT_FOUND_BOOK)
        );

        List<Memo> memoList = memoRepository.findMemoByBook(book);

        List<MemoResDto> memoResDtoList = memoList.stream()
                .map(MemoResDto::new).toList();

        return GlobalResDto.success(memoResDtoList, "success read memos");
    }
}
