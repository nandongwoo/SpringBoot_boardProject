package com.example.boardproject;

import com.example.boardproject.dto.BoardDTO;
import com.example.boardproject.entity.BoardEntity;
import com.example.boardproject.entity.BoardFileEntity;
import com.example.boardproject.repository.BoardRepository;
import com.example.boardproject.service.BoardService;
import com.example.boardproject.util.UtilClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardTest {
    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;


    public BoardDTO testBoard(int i) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardWriter("testWriter" + i);
        boardDTO.setBoardPass("testPass" + i);
        boardDTO.setBoardTitle("testTitle" + i);
        boardDTO.setBoardContents("testContents" + i);
        return boardDTO;
    }


    // 게시글 50개 저장하기
    @Test
    @DisplayName("게시글 데이터 붓기")
    public void dataInsert() {
        IntStream.rangeClosed(1, 50).forEach(i -> {
            BoardDTO boardDTO = testBoard(i);
            ;
            try {
                boardService.save(boardDTO);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    @DisplayName("페이징 객체 확인")
    public void pagingMethod() {
        int page = 0; // index와 같다 0부터 시작
        int pageLimit = 5;
        Page<BoardEntity> boardEntities =
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청페이지에 들어있는 데이터
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // 요청페이지(jpa 기준)
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한페이지에 보여지는 글갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫페이지인지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막페이지인지 여부

        // Page<BoardEntity> -> Page<BoardDTO>
        // map = Page가 제공해주는 메서드, Entity를 바로 DTO로 바꾸면 나중에 위의 식들을 쓰지못하게 될 수있음
        Page<BoardDTO> boardList = boardEntities.map(boardEntity ->
                BoardDTO.builder()
                        .id(boardEntity.getId())
                        .boardTitle(boardEntity.getBoardTitle())
                        .boardWriter(boardEntity.getBoardWriter())
                        .boardHits(boardEntity.getBoardHits())
                        .createdAt(UtilClass.dataTimeFormat(boardEntity.getCreatedAt()))
                        .build());
        System.out.println("boardList.getContent() = " + boardList.getContent()); // 요청페이지에 들어있는 데이터
        System.out.println("boardList.getTotalElements() = " + boardList.getTotalElements()); // 전체 글갯수
        System.out.println("boardList.getNumber() = " + boardList.getNumber()); // 요청페이지(jpa 기준)
        System.out.println("boardList.getTotalPages() = " + boardList.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardList.getSize() = " + boardList.getSize()); // 한페이지에 보여지는 글갯수
        System.out.println("boardList.hasPrevious() = " + boardList.hasPrevious()); // 이전페이지 존재 여부
        System.out.println("boardList.isFirst() = " + boardList.isFirst()); // 첫페이지인지 여부
        System.out.println("boardList.isLast() = " + boardList.isLast()); // 마지막페이지인지 여부
    }

    @Test
    @DisplayName("검색 메서드 확인")
    public void searchMethod() {
        // 제목에 1이 포함된 결과 검색
//        List<BoardEntity> boardEntityList = boardRepository.findByBoardTitleContainingOrderById("1");

        // 제목에 1이 포함된 결과 페이징
        int page = 0;
        int pageLimit = 5;
//        Page<BoardEntity> boardEntities =
//        boardRepository.findByBoardTitleContaining("1", PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        // 제목 또는 작성자에 1이 포함된 결과 페이징
        String q = "1";
        Page<BoardEntity> boardEntities =
                boardRepository.findByBoardTitleContainingOrBoardWriterContaining(q, q, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));


        // BoardEntity를 출력하지 않고 각각을 DTO로 변환해서 출력
//        boardEntityList.forEach(boardEntity -> {
//            System.out.println(BoardDTO.toBoardDTO(boardEntity));
//        });
        Page<BoardDTO> boardList = boardEntities.map(boardEntity ->
                BoardDTO.builder()
                        .id(boardEntity.getId())
                        .boardTitle(boardEntity.getBoardTitle())
                        .boardWriter(boardEntity.getBoardWriter())
                        .boardHits(boardEntity.getBoardHits())
                        .createdAt(UtilClass.dataTimeFormat(boardEntity.getCreatedAt()))
                        .build());
        System.out.println("boardList.getContent() = " + boardList.getContent()); // 요청페이지에 들어있는 데이터
        System.out.println("boardList.getTotalElements() = " + boardList.getTotalElements()); // 전체 글갯수
        System.out.println("boardList.getNumber() = " + boardList.getNumber()); // 요청페이지(jpa 기준)
        System.out.println("boardList.getTotalPages() = " + boardList.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardList.getSize() = " + boardList.getSize()); // 한페이지에 보여지는 글갯수
        System.out.println("boardList.hasPrevious() = " + boardList.hasPrevious()); // 이전페이지 존재 여부
        System.out.println("boardList.isFirst() = " + boardList.isFirst()); // 첫페이지인지 여부
        System.out.println("boardList.isLast() = " + boardList.isLast()); // 마지막페이지인지 여부
    }



    @Test
    @Transactional // 부모 Entity에서 자식 Entity를 조회하는 상황
    @DisplayName("참조관계 확인")
    public void findTest(){
        // BoardEntity 조회
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(55L);
        BoardEntity boardEntity = boardEntityOptional.get();
        // BoardEntity에서 BoardFileEntity 조회
        List<BoardFileEntity> boardFileEntityList = boardEntity.getBoardFileEntityList();
        boardFileEntityList.forEach(boardFileEntity -> {
            System.out.println(boardFileEntity.getOriginalFileName());
            System.out.println(boardFileEntity.getStoredFileName());
        });


    }

}
