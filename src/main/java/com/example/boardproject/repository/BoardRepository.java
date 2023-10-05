package com.example.boardproject.repository;

import com.example.boardproject.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    /*
      update board_table set board_hits+1 where id = ?
      jpql(java persistence query language)
    */


    @Modifying // insert, update, delete
//    1.
      @Query(value = "update BoardEntity b set b.boardHits = b.boardHits+1 where b.id =:id")
//    2.
//      @Query(value = "update board_table set board_hits+1 where id =: id", nativeQuery = true)
    void increaseHist(@Param("id") Long id);

    // select * from board_table where board_title
    List<BoardEntity> findByBoardTitle(String boardTitle);


    // select * from board_table where board_title = ? order by id desc
    List<BoardEntity> findByBoardTitleOrderById(String boardTitle);


    // select * from board_table where board_title like '%q"
    List<BoardEntity> findByBoardTitleContaining(String q);


    // select * from board_table where board_writer list '%q%'
    List<BoardEntity> findByBoardWriterContaining(String q);


    // select * from board_table where board_title like '%q" order by id desc
    List<BoardEntity> findByBoardTitleContainingOrderById(String q);


    // 제목으로 검색한 결과를 page 객체로 리턴 (springframework에 있는 Pageable)
    Page<BoardEntity> findByBoardTitleContaining(String q, Pageable pageable);


    // 작성자로 검색한 결과를 Page 객체로 리턴
    Page<BoardEntity> findByBoardWriterContaining(String q, Pageable pageable);


    // select * from board_table where board_title like '%q%' or board_writer like '%q%' order by id desc
    Page<BoardEntity> findByBoardTitleContainingOrBoardWriterContaining(String q1, String q2, Pageable pageable);

}
