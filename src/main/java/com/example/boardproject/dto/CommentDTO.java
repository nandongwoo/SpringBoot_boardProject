package com.example.boardproject.dto;

import com.example.boardproject.entity.CommentEntity;
import lombok.*;

@Data
@Builder
@NoArgsConstructor // Builder 삽입시 기본생성자 삭제됨
@AllArgsConstructor // 기본생성자 어노테인션 삽입시 Builder에 영향
@ToString
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long boardId;

    public static CommentDTO toCommentDTO(CommentEntity commentEntity){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setBoardId(commentEntity.getBoardEntity().getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        return commentDTO;
    }
}
