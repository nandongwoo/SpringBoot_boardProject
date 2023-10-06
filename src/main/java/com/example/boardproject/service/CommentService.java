package com.example.boardproject.service;

import com.example.boardproject.dto.BoardDTO;
import com.example.boardproject.dto.CommentDTO;
import com.example.boardproject.entity.BoardEntity;
import com.example.boardproject.entity.CommentEntity;
import com.example.boardproject.repository.BoardRepository;
import com.example.boardproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public List<CommentDTO> save(CommentDTO commentDTO) {
        Optional<BoardEntity> byId = boardRepository.findById(commentDTO.getBoardId());
        BoardEntity boardEntity = byId.get();
        CommentEntity commentEntity = CommentEntity.toCommentEntity(boardEntity, commentDTO);
        commentRepository.save(commentEntity);
        List<CommentEntity> commentEntityList = commentRepository.findByBoardEntityOrderByIdDesc(boardEntity);
        // 참조되고있는 서로의 테이블은 부모의 테이블로부터 자식 테이블을 바로 호출할 수있다.
        // ex ) 위의 코드의 경우에도 각 테이블이 서로 참조관계이기 때문에 commentRepository을 호출했지만 boardEntity를 불러올 수있다.
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity1 : commentEntityList){
            CommentDTO commentDTO1 = CommentDTO.toCommentDTO(commentEntity1);
            commentDTOList.add(commentDTO1);
        }
        return commentDTOList;
    }
}