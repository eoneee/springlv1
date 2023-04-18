package com.example.springlv1.dto;

import com.example.springlv1.entity.Crud;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CrudResponseDto {
    private Long id;
    private String title;
    private String author;
    private String content;

    public CrudResponseDto(Crud crud) {
        this.id = crud.getId();
        this.title = crud.getTitle();
        this.author = crud.getAuthor();
        this.content = crud.getContent();
    }
}
