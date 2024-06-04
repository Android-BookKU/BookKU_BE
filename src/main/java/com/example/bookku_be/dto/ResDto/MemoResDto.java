package com.example.bookku_be.dto.ResDto;

import com.example.bookku_be.domain.Memo;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemoResDto {

    @Column(nullable = false)
    Long memoId;
    @Column(nullable = false)
    String content;

    public MemoResDto(Memo memo) {
        this.memoId = memo.getMemoId();
        this.content = memo.getContent();
    }
}
