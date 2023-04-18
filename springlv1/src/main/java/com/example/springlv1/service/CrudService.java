package com.example.springlv1.service;

import com.example.springlv1.dto.CrudRequestDto;
import com.example.springlv1.dto.CrudResponseDto;
import com.example.springlv1.entity.Crud;
import com.example.springlv1.repository.CrudRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

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
//        return crudRepository.createCrud(crud);
        crudRepository.save(crud);
        return "저장되었습니다.";
    }

    //메인 페이지
    public List<CrudResponseDto> getCrudList(){
        //테이블에 저장되어있는 모든 글을 조회
        return crudRepository.findAll().stream().map(CrudResponseDto::new).collect(Collectors.toList());

    }

    //전체목록 말고 하나씩 보기
    public CrudResponseDto getCrud(Long id){
        //조회하기 위해 받아온 crud의 id를 사용해서 해당 crud인스턴스가 테이블에 존재 하는지 확인하고 가져오기
        //Crud crud = table.get(id);->repository한테서 id를 가져오면 됨
        Crud crud = checkCrud(id);
//        위에서 예외 처리 해줌
//        if(crud != null){
//            return new CrudResponseDto(crud);
//        }else{
//            return new CrudResponseDto();
//        }
        return new CrudResponseDto(crud);
    }

    //수정하기
    @Transactional
    public CrudResponseDto updateCrud(Long id,CrudRequestDto requestDto){
        //수정하기 위해 받아온 crud의 id를 사용하여 해당 crud 인스턴스가 존재하는지 확인하고 가져오기
        //Crud crud = table.get(id);
        //게시글이 id가 null인지확인
        Crud crud = checkCrud(id);

        //비밀번호 일치하는지 확인
        if(requestDto.getPassword().equals(crud.getPassword())){
            crud.update(requestDto);
            return new CrudResponseDto(crud);
//            return new CrudResponseDto(crud);
        }else{
            return new CrudResponseDto(crud);
//            return new CrudResponseDto(crud);
        }
//        Crud crud = crudRepository.fi(id);
//        if(crud != null){
//            crud.update(requestDto);
//            return new CrudResponseDto(crud);
//        }else{
//            return new CrudResponseDto();
//        }



    }


    //삭제
    public String deleteCrud(Long id,String password){
        //삭제하기 위해 받아온 crud의 id를 사용하여 해당 crud 인스턴스가 존재하는지 확인하고 가져오기
//        Crud crud = table.get(id);
        Crud crud = checkCrud(id);
        if(crud.getPassword().equals(password))
            //spring jpa가 되면서 id->crud
            crudRepository.delete(crud);

//        crudRepository.delete(id);
//        Crud crud = crudRepository.getCrud(id);
//        if(crud != null){
////            table.remove(id);
//            crudRepository.deleteCrud(id);
//            return "삭제 성공";
//        }else{
//            return "삭제할 내용이 없습니다.";
//        }
        return "삭제 성공";
    }

    private Crud checkCrud(Long id) {
        Crud crud = crudRepository.findById(id).orElseThrow(
                ()->new NullPointerException("글이 존재하지 않습니다.")
        );
        return crud;
    }

    public CrudResponseDto getCrudByTitle(String title) {
        Crud crud = crudRepository.findByTitle(title).orElseThrow(
                () -> new NullPointerException("해당하는 제목의 글이 없습니다.")
        );
        return new CrudResponseDto(crud);
    }
}
