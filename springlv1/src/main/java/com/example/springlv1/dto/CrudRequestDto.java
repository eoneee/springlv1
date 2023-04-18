package com.example.springlv1.dto;

import lombok.Getter;

@Getter
public class CrudRequestDto {
    private String title;
    private String author;
    private String content;
    private Integer password;

}
