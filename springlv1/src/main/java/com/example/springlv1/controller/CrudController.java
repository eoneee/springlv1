package com.example.springlv1.controller;

import com.example.springlv1.dto.CrudRequestDto;
import com.example.springlv1.dto.CrudResponseDto;
import com.example.springlv1.entity.Crud;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.stream.Collectors;

@RestController
//클라이언츠의 요청을 하나씩 연결해줌
@RequestMapping("/api")
public class CrudController {

    private static final Map<Long, Crud> table = new HashMap<>();
    private static long ID;

    //글 생성하기
    @PostMapping("/post")
    public String createCrud(@RequestBody CrudRequestDto requestDto){
        //브라우저에서 받아온 데이터를 저장하기 위해서 crud객체로 변환
        Crud crud = new Crud(requestDto);

        //ID 중복을 막기위해서 현재 table을 최대 ID + 1
        crud.setId(++ID);
        //테이블에 생성한 Crud 인스턴스를 저장
        table.put(ID,crud);
        return "게시글이 등록되었습니다";
    }

    //메인 페이지
    @GetMapping("/posts")
    public List<CrudResponseDto>getCrudList(){
        //테이블에 저장되어있는 모든 글을 조회
        return table.values().stream().map(CrudResponseDto::new).collect(Collectors.toList());

    }

    //전체목록 말고 하나씩 보기
    @GetMapping("post/{id}")
    public CrudResponseDto getCrud(@PathVariable Long id){
        //조회하기 위해 받아온 crud의 id를 사용해서 해당 crud인스턴스가 테이블에 존재 하는지 확인하고 가져오기
        Crud crud = table.get(id);
        if(crud != null){
            return new CrudResponseDto(crud);
        }else{
            return new CrudResponseDto();
        }
    }

    //수정하기
    @PutMapping("/post/{id}")
    public CrudResponseDto updateCrud(@PathVariable Long id,@RequestBody CrudRequestDto requestDto){
        //수정하기 위해 받아온 crud의 id를 사용하여 해당 crud 인스턴스가 존재하는지 확인하고 가져오기
        Crud crud = table.get(id);
        if(crud != null){
            crud.update(requestDto);
            return new CrudResponseDto(crud);
        }else{
            return new CrudResponseDto();
        }
    }


    //삭제
    @DeleteMapping("/post/{id}")
    public String deleteCrud(@PathVariable Long id){
        //삭제하기 위해 받아온 crud의 id를 사용하여 해당 crud 인스턴스가 존재하는지 확인하고 가져오기
        Crud crud = table.get(id);
        if(crud != null){
            table.remove(id);
            return "삭제 성공";
        }else{
            return "삭제할 내용이 없습니다.";
        }
    }

}
