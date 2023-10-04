package com.example.boardproject.dto;

import com.example.boardproject.entity.BoardEntity;
import com.example.boardproject.util.UtilClass;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Data
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardTitle;
    private String boardPass;
    private String boardContents;
    private String createdAt;
//    private List<MultipartFile> boardFileName;
    private int boardHits;


    public static BoardDTO toBoardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardPass(boardEntity.getBoardPass());
//        boardDTO.setCreatedAt(boardEntity.getCreatedAt());
        boardDTO.setCreatedAt(UtilClass.dataTimeFormat(boardEntity.getCreatedAt()));
        return boardDTO;
    }
}
