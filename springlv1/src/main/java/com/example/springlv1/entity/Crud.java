package com.example.springlv1.entity;
import com.example.springlv1.dto.CrudRequestDto;
import lombok.Getter;

@Getter
public class Crud {
    private Long id;
    //private이기 때문에 controller에서 못쓰니까 method -> Getter
    private String title;
    private String author;
    private String content;
    private Integer password;

    public void setId(Long id) {
        this.id = id;
    }

    public Crud(CrudRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
        this.content = requestDto.getContent();
    }

    public void update(CrudRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
    }

    //Getter method -> @Getter로 선언하면 없애도 되는 부분
//    public Long getId() {
//        return id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public String getContent() {
//        return content;
//    }

}
