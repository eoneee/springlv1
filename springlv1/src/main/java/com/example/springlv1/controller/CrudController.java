package com.example.springlv1.controller;

import com.example.springlv1.dto.CrudRequestDto;
import com.example.springlv1.dto.CrudResponseDto;
import com.example.springlv1.entity.Crud;
import com.example.springlv1.service.CrudService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.stream.Collectors;

@RestController
//클라이언츠의 요청을 하나씩 연결해줌
@RequestMapping("/api")
public class CrudController {

    private final CrudService crudService = new CrudService();

    //글 생성하기
    @PostMapping("/post")
    public String createCrud(@RequestBody CrudRequestDto requestDto) {
        //브라우저에서 요청해온 데이터를 잘 불러와서 서비스에 던져줌
        String message = crudService.createCrud(requestDto);
        return message;
    }

    //메인 페이지
    @GetMapping("/posts")
    public List<CrudResponseDto> getCrudList() {
        return crudService.getCrudList();
    }

    //전체목록 말고 하나씩 보기
    @GetMapping("post/{id}")
    public CrudResponseDto getCrud(@PathVariable Long id) {
        return crudService.getCrud(id);
    }

    //수정하기
    @PutMapping("/post/{id}")
    public CrudResponseDto updateCrud(@PathVariable Long id, @RequestBody CrudRequestDto requestDto) {
        return crudService.updateCrud(id,requestDto);
    }


    //삭제
    @DeleteMapping("/post/{id}")
    public String deleteCrud(@PathVariable Long id) {
        return  crudService.deleteCrud(id);

    }
}
