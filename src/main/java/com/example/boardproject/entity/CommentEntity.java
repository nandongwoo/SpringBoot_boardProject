package com.example.boardproject.entity;

import com.example.boardproject.dto.CommentDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "comment_table")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String commentWriter;
    @Column(length = 200, nullable = false)
    private String commentContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;


    public static CommentEntity toCommentEntity(BoardEntity boardEntity, CommentDTO commentDTO){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setBoardEntity(boardEntity);
        // CommentEntity에서 boardId는 DB-Table에 이름을 설정해준 것 뿐이고 실제필드는 BoardEntity클래스로 지정되었기 때문에
        // 값을 넣을 때는 service에서 받아온 boardEntity를 넣어주어야하고
        // commentEntity.setBoardEntity(boardEntity); 로 설정을 해주면 boardEntity의 @id 어노테인션으로 지정 된
        // 필드 값이 DB의 comment_table의 board_id 컬럼값으로 자동으로 들어감.


        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        return commentEntity;
    }

}
