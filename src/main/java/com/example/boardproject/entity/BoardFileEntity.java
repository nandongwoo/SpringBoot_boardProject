//package com.example.boardproject.entity;
//
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter(AccessLevel.PRIVATE)
//@Table(name = "board_File_Table")
//public class BoardFileEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column
//    private Long boardId;
//    @Column
//    private String originalFileName;
//    @Column
//    private String storedFileName;
//
//
//    public static BoardFileEntity toSaveBoardFile(Long boardId,String originalFileName, String storedFileName){
//        BoardFileEntity boardFileEntity = new BoardFileEntity();
//        boardFileEntity.setBoardId(boardId);
//        boardFileEntity.setOriginalFileName(originalFileName);
//        boardFileEntity.setStoredFileName(storedFileName);
//        return boardFileEntity;
//    }
//}
