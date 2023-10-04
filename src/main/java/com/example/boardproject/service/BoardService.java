package com.example.boardproject.service;

import com.example.boardproject.dto.BoardDTO;
import com.example.boardproject.entity.BoardEntity;
import com.example.boardproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Long save(BoardDTO boardDTO) {
//        if (boardDTO.getBoardFileName().get(0).isEmpty()) {
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
            return boardRepository.save(boardEntity).getId();
//        }
//        else {
//            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
//            Long boardId = boardRepository.save(boardEntity).getId();
//            for (MultipartFile boardFile : boardDTO.getBoardFileName()) {
//                String originalFileName = boardFile.getOriginalFilename();
//                String storedFileName = System.currentTimeMillis() + "-" + originalFileName;
//
//                BoardFileEntity boardFileEntity = BoardFileEntity.toSaveBoardFile(boardId, originalFileName,storedFileName);
//
//                String savePath = "D:\\springBoot_board_img\\" + storedFileName;
//                boardFile.transferTo(new File(savePath));
//                boardRepository.save(boardFileEntity);
//
//            }
//        }
    }

    public List<BoardDTO> findAll() {
//        List<BoardEntity> boardEntityList = boardRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
//        Sort정렬 기준


        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardRepository.findAll()){
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            boardDTOList.add(boardDTO);
        }
        return boardDTOList;
    }

    public Object findById(Long id) {
        return BoardDTO.toBoardDTO(boardRepository.findById(id).orElseThrow(() -> new NoSuchElementException()));
    }

    /**
     * 서비스 클래스 메서드에서 @Transactional 붙이는 경우
     * 1. jpql로 작성한 메서드 호출할 때
     * 2. 부모엔티티에서 자식엔티티를 바로 호출할 때
     */

    @Transactional
    public void increaseHits(Long id) {
        boardRepository.increaseHist(id);
    }
}











