package com.example.boardproject.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "board_File_Table")
public class BoardFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //
    @ManyToOne(fetch = FetchType.LAZY) //다대일 = boardFileTalbe이 "다" boardTalbe이 "일" /  하나의 게시물에 여러개의 파일 o / 여러개의 게시물에 하나의 파일 x
    @JoinColumn(name = "board_id") // DB에 생성 될 참조 컬럼의 이름, DB에 데이터를 넣을 때엔 board_id만 넣어서는 안되고
                                    // Entity 전체를 넣어줘야한다
    private BoardEntity boardEntity; // 부모 엔티티 타입으로 정의

    @Column
    private String originalFileName;
    @Column
    private String storedFileName;


    public static BoardFileEntity toSaveBoardFile(BoardEntity savedEntity,String originalFileName, String storedFileName){
        BoardFileEntity boardFileEntity = new BoardFileEntity();
        boardFileEntity.setBoardEntity(savedEntity);
        boardFileEntity.setOriginalFileName(originalFileName);
        boardFileEntity.setStoredFileName(storedFileName);
        return boardFileEntity;
    }
}
