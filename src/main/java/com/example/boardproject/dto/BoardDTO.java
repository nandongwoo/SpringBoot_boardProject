package com.example.boardproject.dto;

import com.example.boardproject.entity.BoardEntity;
import com.example.boardproject.util.UtilClass;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor // Builder 삽입시 기본생성자 삭제됨
@AllArgsConstructor // 기본생성자 어노테인션 삽입시 Builder에 영향
@ToString
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardTitle;
    private String boardPass;
    private String boardContents;
    private String createdAt;
    //    private List<MultipartFile> boardFileName;
    private int boardHits;


    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardPass(boardEntity.getBoardPass());
//        boardDTO.setCreatedAt(boardEntity.getCreatedAt());
        boardDTO.setCreatedAt(UtilClass.dataTimeFormat(boardEntity.getCreatedAt()));


//        BoardDTO boardDTO = BoardDTO.builder()
//                .id(boardEntity.getId())
//                .boardWriter(boardEntity.getBoardWriter())
//                .boardTitle(boardEntity.getBoardTitle())
//                .boardPass(boardEntity.getBoardPass())
//                .boardContents(boardEntity.getBoardContents())
//                .createdAt(UtilClass.dataTimeFormat(boardEntity.getCreatedAt()))
//                .build();
        return boardDTO;
    }
}
