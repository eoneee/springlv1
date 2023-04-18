package com.example.springlv1.service;

import com.example.springlv1.dto.CrudRequestDto;
import com.example.springlv1.dto.CrudResponseDto;
import com.example.springlv1.entity.Crud;
import com.example.springlv1.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrudService {

    private final CrudRepository crudRepository;
    @Autowired
    public CrudService(CrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    //글 생성하기
    public String createCrud(CrudRequestDto requestDto){
        //브라우저에서 받아온 데이터를 저장하기 위해서 crud객체로 변환
        Crud crud = new Crud(requestDto);
        //가져와서 객체로 변환한것을 리턴해줌 -> repository한테
        return crudRepository.createCrud(crud);
    }

    //메인 페이지
    public List<CrudResponseDto> getCrudList(){
        //테이블에 저장되어있는 모든 글을 조회
        return crudRepository.getCrudList();

    }

    //전체목록 말고 하나씩 보기
    public CrudResponseDto getCrud(Long id){
        //조회하기 위해 받아온 crud의 id를 사용해서 해당 crud인스턴스가 테이블에 존재 하는지 확인하고 가져오기
        //Crud crud = table.get(id);->repository한테서 id를 가져오면 됨
        Crud crud = crudRepository.getCrud(id);

        if(crud != null){
            return new CrudResponseDto(crud);
        }else{
            return new CrudResponseDto();
        }
    }

    //수정하기
    public CrudResponseDto updateCrud(Long id,CrudRequestDto requestDto){
        //수정하기 위해 받아온 crud의 id를 사용하여 해당 crud 인스턴스가 존재하는지 확인하고 가져오기
//        Crud crud = table.get(id);
        Crud crud = crudRepository.getCrud(id);
        if(crud != null){
            crud.update(requestDto);
            return new CrudResponseDto(crud);
        }else{
            return new CrudResponseDto();
        }
    }


    //삭제
    public String deleteCrud(Long id){
        //삭제하기 위해 받아온 crud의 id를 사용하여 해당 crud 인스턴스가 존재하는지 확인하고 가져오기
//        Crud crud = table.get(id);
        Crud crud = crudRepository.getCrud(id);
        if(crud != null){
//            table.remove(id);
            crudRepository.deleteCrud(id);
            return "삭제 성공";
        }else{
            return "삭제할 내용이 없습니다.";
        }
    }

}