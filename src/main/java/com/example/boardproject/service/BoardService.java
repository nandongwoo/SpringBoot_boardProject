package com.example.boardproject.service;

import com.example.boardproject.dto.BoardDTO;
import com.example.boardproject.entity.BoardEntity;
import com.example.boardproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
}











