package com.example.boardproject.repository;

import com.example.boardproject.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    /*
      update board_table set board_hits+1 where id = ?
      jpql(java persistence query language)
    */


    @Modifying // insert, update, delete
//    1.
      @Query(value = "update BoardEntity b set b.boardHits = b.boardHits+1 where b.id =:id")
//    2.
//      @Query(value = "update BoardEntity b set b.boardHits = b.boardHits+1 where b.id =:id", nativeQuery = true)
    void increaseHist(@Param("id") Long id);
}
